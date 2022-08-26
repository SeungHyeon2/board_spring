package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.ReplyDAO;
import com.board.domain.ReplyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReplyServiceImpl implements ReplyService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ReplyDAO dao;

	// 댓글 조회
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return dao.list(bno);
	}

	// 댓글 작성
	@Override
	public void write(ReplyVO vo) throws Exception {
		dao.write(vo);
		logger.info("ReplyServiceImpl Write 메소드 동작");
	}

	// 대댓글 작성
	@Override
	public void reWrite(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.reWrite(vo);
		logger.info("ReplyServiceImpl reWrite 메소드 동작");
	}
	
	// 댓글 수정
	@Override
	public void modify(ReplyVO vo) throws Exception {
		dao.modify(vo);
	}

	// 댓글 삭제
	@Override
	public void delete(ReplyVO vo) throws Exception {
		dao.delete(vo);
	}


}
