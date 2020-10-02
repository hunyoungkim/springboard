package hun.b.board.DAO;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hun.b.board.VO.boardVO;

@Repository
public class boardDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<boardVO> list(int startrow) {
		return sqlSessionTemplate.selectList("selectAll", startrow);
	}

	public String numcount() {
		return sqlSessionTemplate.selectOne("NumCount");
	}

	public void add(boardVO VO) {
		sqlSessionTemplate.insert("add", VO);

	}

	public boardVO view(int num) {
		return sqlSessionTemplate.selectOne("select", num);
	}

	public void update(boardVO VO) {
		sqlSessionTemplate.update("update", VO);
	}

	public int listcount() {
		return sqlSessionTemplate.selectOne("ListCount");
	}

	public void readcount(boardVO VO) {
		sqlSessionTemplate.update("readcount", VO);
	}

	public int searchcount(HashMap<String, Object> valueMap) {

		return sqlSessionTemplate.selectOne("searchcount", valueMap);
	}
	public List<boardVO> searchlist(HashMap<String, Object> valueMap) {
		return sqlSessionTemplate.selectList("searchlist", valueMap);
	}
	public void delete(int num) {
		sqlSessionTemplate.delete("delete", num);
	}
	public int replynum() {
		return sqlSessionTemplate.selectOne("replynum");
	}
	public void answerup(HashMap<String, Object> valueMap) {
		sqlSessionTemplate.update("answer", valueMap);
	}
	public void reply(boardVO VO) {
		sqlSessionTemplate.insert("reply", VO);

	}
	
}
