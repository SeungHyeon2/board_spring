package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.BoardVO;

public interface BoardDAO {
	
	// 게시물 목록
	public List<BoardVO> list() throws Exception;

	// 게시물 작성
	public void write(BoardVO vo) throws Exception;
	
	// 게시물 조회
	public BoardVO view(int bno) throws Exception;
	
	// 게시물 조회수
	public BoardVO viewCnt(int bno) throws Exception;
	
	//게시물 수정
	public void modify(BoardVO vo) throws Exception;
	
	// 게시물 삭제
	public void delete(int bno) throws Exception;
	
	// 게시물 삭제여부
	public String delYN(int bno) throws Exception;
	
	// 첨부파일 업로드
	public void insertFile(Map<String, Object> map) throws Exception;
	
	// 첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	
	// 첨부파일 다운로드
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	// 첨부파일 수정
	public void modifyFile(int bno) throws Exception;
	
	// 게시물 총 갯수
	public int count() throws Exception;
	
	// 게시물 목록 + 페이징
	public List listPage(int displayPost, int postNum) throws Exception;

	// 게시물 목록 + 페이징 + 검색
	public List<BoardVO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception;
	
	// 게시물 총 개수 + 검색 적용
	public int searchCount(String searchType, String keyword) throws Exception;
	

}