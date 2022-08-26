package com.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.domain.FileVO;
import com.board.util.FileUtils;
import com.board.util.UUIDgeneration;

import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class BoardServiceImpl implements BoardService, Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private BoardDAO dao;
	
	@Resource
	private FileUtils fileUtils;
	
	// 게시물 목록
	@Override
	public List<BoardVO> list() throws Exception {
		return dao.list();
	}
	
	// 게시물 작성
	@Override
	@Async
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
		dao.write(vo);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest);
		int size = list.size();
		for(int i=0; i<size; i++) {
			dao.insertFile(list.get(i));
		}
	}	
	
	// 게시물 조회
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO view(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.view(bno);
	}
	
	// 게시물 수정
	@Override
	@Async
	public void modify(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.modify(vo);
	}

	// 게시물 삭제
	@Override
	@Async
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);	
	}
	
	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(bno);
	}
	
	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(map);
	}
	
	// 첨부파일 수정(삭제)
	//이부분 수정해야함
	@Override
	public void modifyFile(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.modifyFile(bno);
	}
	
	
	// 게시물 총 갯수
	@Override
	public int count() throws Exception {
		return dao.count();
	}
	
	// 게시물 목록 + 페이징
	@Override
	public List listPage(int displayPost, int postNum) throws Exception {
		return dao.listPage(displayPost, postNum);
	}
	
	// 게시물 목록 + 페이징 + 검색
	@Override
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword)
			throws Exception {
		
		// uuid testing
		UUIDgeneration uuid = new UUIDgeneration();
		String id = uuid.getUUID();
		System.out.println(id);
		return dao.listPageSearch(displayPost, postNum, searchType, keyword);
	}

	// 게시물 검색
	@Override
	public int searchCount(String searchType, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchCount(searchType, keyword);
	}

	
	// 게시물 삭제 여부
	@Override
	public void delYN(int bno) throws Exception {
		dao.delete(bno);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
	}

	



	
	
	
	
}