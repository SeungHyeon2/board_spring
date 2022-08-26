package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.AccountDataVO;

@Repository
public class AccountDataDAOImpl implements AccountDataDAO {

	@Inject
	private SqlSession sql;
	
	private static final String namespace = "com.board.mappers.accountData";
	
	@Override
	public void ta_insert(AccountDataVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".ta_insert", vo);
	}

	@Override
	public List<AccountDataVO> ta_getByListForAll() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".ta_getByListForAll");
	}

	@Override
	public AccountDataVO ta_getByOneForIdAndPw(String ta_id, String ta_pw) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ta_id", ta_id);
		paramMap.put("ta_pw", ta_pw);
		return sql.selectOne(namespace+".ta_getByOneForIdAndPw", paramMap);
	}

	@Override
	public AccountDataVO ta_getByOneForId(String ta_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".ta_getByOneForId", ta_id);
	}

	@Override
	public AccountDataVO ta_getByOneForPw(String ta_pw) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".ta_getByOneForPw", ta_pw);
	}

	@Override
	public void ta_updateForIdx(AccountDataVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace+".ta_updateForIdx", vo);
	}

	@Override
	public void ta_deleteForIndex(int ta_idx) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace+".ta_deleteForIndex", ta_idx);
	}

}
