package com.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	// 회원 가입
	@Override
	public void register(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.register(vo);
	}

	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	// 회원정보 수정
	@Override
	public void modify(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.modify(vo);
	}
	
	// 회원 탈퇴
	@Override
	public void withdrawal(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.withdrawal(vo);
		
	}

	@Override
	public MemberVO idCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.idCheck(userId);
	}
}
