package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.board";
	
	@Override
	public List<BoardVO> list() throws Exception {
		
		return sql.selectList(namespace + ".list");
	}
	
	
	// 게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		sql.insert(namespace + ".write", vo);
		
	}
	
	// 게시물 조회
	@Override
	public BoardVO view(int bno) throws Exception {
		sql.selectOne(namespace + ".viewCnt", bno);
		return sql.selectOne(namespace + ".view", bno);
	}
	
	// 게시물 조회수
	@Override
	public BoardVO viewCnt(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".viewCnt", bno);
	}
	
	// 게시물 수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".delete", bno);
		
	}

	// 게시물 총 갯수
	@Override
	public int count() throws Exception {
		return sql.selectOne(namespace + ".count"); 
	}
	
	//첨부파일 업로드
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sql.insert(namespace + ".insertFile", map);
	}
	
	//첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".selectFileList", bno);
	}

	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".selectFileInfo", map);
	}
	
	// 첨부파일 수정
	@Override
	public void modifyFile(int bno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".modifyFile", bno);
	}
	

	// 게시물 목록 + 페이징
	@Override
	public List listPage(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
	  
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		return sql.selectList(namespace + ".listPage", data);
	}

	@Override
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword)
			throws Exception {
		
		// HashMap 생성
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		// displayPost, PostNum, searchType, keyword 생성
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace + ".listPageSearch", data);
	}

	@Override
	public int searchCount(String searchType, String keyword) throws Exception {
		// TODO Auto-generated method stub
		
		// HashMap 생성
		HashMap data = new HashMap();
		
		// searchType, keyword생성
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sql.selectOne(namespace + ".searchCount", data);
	}

	// 게시물 삭제 여부
	@Override
	public String delYN(int bno) throws Exception {
		
		return sql.selectOne(namespace + ".delYN", bno); 
		// TODO Auto-generated method stub
		
		
	}


	



}
