package com.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.member";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".register", vo);
	}
	
	
	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".loginBcrypt", vo);
	}


	// 회원 정보 수정
	@Override
	public void modify(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".modify", vo);
	}


	// 회원 탈퇴
	@Override
	public void withdrawal(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".withdrawal", vo);
	}

	
	// 아이디 중복 체크
	@Override
	public MemberVO idCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".idCheck", userId);
	}
	

}
