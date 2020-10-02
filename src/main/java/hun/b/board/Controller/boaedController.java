package hun.b.board.Controller;





import java.io.File;
import java.net.URLEncoder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import hun.b.board.Service.boardService;
import hun.b.board.VO.boardVO;


@Controller
public class boaedController {

	@Autowired
	private boardService Service;

	private int page=0;
	private int limit=0;
	
	@RequestMapping(value = "/listAll")
	public String listAll(Model model, HttpServletRequest request) {
		page=1;
		limit=10;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		int listcount = Service.listcount(); //18
		int maxpage = Service.maxpage(listcount, limit); //18 10
		//System.out.println(maxpage);
		int startpage = Service.startpage(page);
		int endpage = Service.endpage(startpage, maxpage);
		
		model.addAttribute("page",page);
	
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		
		model.addAttribute("list", Service.listAll(page, limit));
		return "./boardlist/boardlist";
	}
	@RequestMapping(value = "/Insert", method = RequestMethod.GET)
	public String add() {
		return "./boardInsert/insert";
	}
	@RequestMapping(value = "/Insert", method = RequestMethod.POST)
	public String add(boardVO VO, MultipartHttpServletRequest request) throws Exception {
		
		if(!VO.getFilename().isEmpty()) {
		String fileName = VO.getFilename().getOriginalFilename();
		CommonsMultipartFile commons = (CommonsMultipartFile) VO.getFilename();
		System.out.println(fileName);
		String savePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		String filePath=savePath + "\\" + fileName;
		File file = new File(filePath);
		commons.transferTo(file);
		VO.setAttached_file(fileName);
		}
		Service.add(VO);
		return "redirect:http://localhost:8088/b/listAll";
	}
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(Model model,boardVO VO) {
		Service.readcount(VO);
		model.addAttribute("view" , Service.select(VO.getNum()));
		return "./boardview/boardview";
	}
	@RequestMapping(value = "/down", method = RequestMethod.GET)
	public String down(@RequestParam(value = "attached_file", required = true) String attached_file, HttpServletResponse response) throws Exception {
		attached_file = URLEncoder.encode(attached_file, "UTF-8").replaceAll("\\+", "%20");
		System.out.println(attached_file);
		response.setContentType("application/octet-stream");
		return "redirect:/resources/upload/"+attached_file;
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String Update(Model model,  boardVO VO) {
		model.addAttribute("olddate", Service.select(VO.getNum()));
		return "boardupdate/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String Update(boardVO VO, MultipartHttpServletRequest request) throws Exception {
		if(!VO.getFilename().isEmpty()) {
			System.out.println(VO.getFilename());
		String fileName = VO.getFilename().getOriginalFilename();
		CommonsMultipartFile commons = (CommonsMultipartFile) VO.getFilename();
		System.out.println(fileName);
		String savePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		String filePath=savePath + "\\" + fileName;
		File file = new File(filePath);
		commons.transferTo(file);
		VO.setAttached_file(fileName);
		}
		System.out.println(VO);
		System.out.println("Filename :" + VO.getFilename());
		System.out.println("Attached_file :"+VO.getAttached_file());
		Service.update(VO);
		return "redirect:http://localhost:8088/b/listAll";
	}
	@RequestMapping(value = "/search")
	public String search(Model model, HttpServletRequest request) {
		String keyword= request.getParameter("keyword");
		String keyfield = request.getParameter("keyfield");
		int searchlistcount = Service.searchcount(keyword, keyfield);
//		System.out.println("-------------컨트롤러---------------");
//		System.out.println(searchlistcount);
		page=1;
		limit=10;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int maxpage = Service.maxpage(searchlistcount , limit);
		int startpage = Service.startpage(page);
		int endpage = Service.endpage(startpage, maxpage);
		
		System.out.println(keyfield);
		System.out.println(keyword);
		
		model.addAttribute("page",page);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("searchlistcount", searchlistcount);
		model.addAttribute("searchlist", Service.searchlist(keyword, keyfield, startpage, endpage, page));
		return "./search/searchlist";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(boardVO VO) {
		Service.remove(VO.getNum());
		return "redirect:http://localhost:8088/b/listAll";
	}
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String replymove(Model model, boardVO VO ) {
		model.addAttribute("reply" , Service.select(VO.getNum()));
		return "./boardreply/reply";
	}
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(boardVO VO, MultipartHttpServletRequest request) throws Exception {
		if(!VO.getFilename().isEmpty()) {
		String fileName = VO.getFilename().getOriginalFilename();
		CommonsMultipartFile commons = (CommonsMultipartFile) VO.getFilename();
		System.out.println(fileName);
		String savePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		String filePath=savePath + "\\" + fileName;
		File file = new File(filePath);
		commons.transferTo(file);
		VO.setAttached_file(fileName);
		}
		System.out.println("리플 컨트롤러");
		Service.reply(VO);
		return "redirect:http://localhost:8088/b/listAll";
	}
	
}
