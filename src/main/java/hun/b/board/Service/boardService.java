package hun.b.board.Service;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hun.b.board.DAO.boardDAO;
import hun.b.board.VO.boardVO;

@Service
public class boardService {


	
	@Autowired
	private boardDAO DAO;
	
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();

	
	public int listcount() {
		int listcount = DAO.listcount();
		return listcount;
	}
	public int maxpage(int listcount,int limit) {
		int maxpage = (int)((double) listcount/limit +0.95);
		return maxpage;
	}
	public int startpage(int page) {
		int startpage = (((int)((double) page/10+0.9))-1)*10 +1;
		return startpage;
	}
	public int endpage(int startpage, int maxpage) {
		int endpage = startpage +10 -1;
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		return endpage;
	}
	public List<boardVO> listAll(int page, int limit){	

		int startrow = (page-1)*10+1;
		//int endrow =startrow+10-1;
		return DAO.list(startrow);		
	}
	public void add(boardVO VO) {
		String count = DAO.numcount();
		int num;
		if(count == null ) {
		num=1;
		
		}else {
		num = Integer.parseInt(count) + 1;
		}
		VO.setNum(num);
		System.out.println(VO);
		DAO.add(VO);
	}
	public boardVO select(int num) {
		return DAO.view(num);
	}
	public void update(boardVO VO) {
		DAO.update(VO);
	}
	public void readcount(boardVO VO) {
		DAO.readcount(VO);
	}
	public int searchcount(String keyword,String keyfield) {
//		String searchCall = "";
//		if (!"".equals(keyword)) {
//		if ("all".equals(keyfield)) { 
//			searchCall =  "(subject like '%'||'" + keyword + "'||'%') or ( m_id like '%'||'" + keyword + "'||'%') or ( content like '%'||'" + keyword + "'||'%')";
//		} else if ("subject".equals(keyfield)) { 
//			searchCall = " subject like '%' || '" + keyword + "' || '%'";
//		} else if ("m_id".equals(keyfield)) { 
//			searchCall = " m_id like '%' || '" + keyword + "' || '%'";
//		} else if ("content".equals(keyfield)) { 
//			searchCall = " content like '%' || '" + keyword + "' || '%'";
//		} 
//		}
		valueMap.put("keyword", keyword);
		valueMap.put("keyfield", keyfield);
		
		 return DAO.searchcount(valueMap);

	}
	public List<boardVO> searchlist(String keyword,String keyfield, int startpage, int endpage, int page){
//		String searchCall = "";
//		if (!"".equals(keyword)) {
//		if ("all".equals(keyfield)) {
//			searchCall = "(subject like '%' || '" + keyword + "' || '%') or ( m_id like '%' || '" + keyword + "' || '%') or ( content like '%' || '" + keyword + "' || '%')";
//		} else if ("subject".equals(keyfield)) { 
//			searchCall = " subject like '%' || '" + keyword + "' || '%'";
//		} else if ("m_id".equals(keyfield)) { 
//			searchCall = " m_id like '%' || '" + keyword + "' || '%'";
//		} else if ("content".equals(keyfield)) { 
//			searchCall = " content like '%' || '" + keyword + "' || '%'";
//		} 
//		}
//		System.out.println(searchCall);
//		ArrayList<Object> alist = new ArrayList<>();
//		alist.add(searchCall);
//		alist.add(startpage);
//		alist.add(endpage);
		int startrow = (page-1)*10+1;
		int endrow = startpage + 10 - 1;
		valueMap.put("keyword", keyword);
		valueMap.put("keyfield", keyfield);
		valueMap.put("startpage", startrow);
		valueMap.put("endpage", endrow);
		
		return DAO.searchlist(valueMap);
		
	}
	public void remove(int num) {
		DAO.delete(num);
	}
	public void reply(boardVO VO) {
		System.out.println("리플 서비스 시작");
		System.out.println(VO);
		int num = DAO.replynum();
		if (num != 0) {
			num = num + 1;
		} else {
			num = 1;
		}
		VO.setNum(num);
		valueMap.put("answer_num", VO.getAnswer_num());
		valueMap.put("answer_seq", VO.getAnswer_seq());
		DAO.answerup(valueMap);
		VO.setAnswer_seq(VO.getAnswer_seq()+1);
		VO.setAnswer_lev(VO.getAnswer_lev()+1);
		System.out.println("=====입력전========== ");
		System.out.println(VO);
		DAO.reply(VO);
		
	}
	
}
