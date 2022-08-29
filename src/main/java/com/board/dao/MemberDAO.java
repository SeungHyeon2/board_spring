package com.board.dao;

import com.board.domain.MemberVO;

public interface MemberDAO {
	
	// 회원 가입
	public void register(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;
	
	// 회원 정보 수정
	public void modify(MemberVO vo) throws Exception;
	
	// 회원 탈퇴
	public void withdrawal(MemberVO vo) throws Exception;
	
	// 아이디 중복 체크
	public MemberVO idCheck(String userId) throws Exception;
	
}
