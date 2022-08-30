package com.board.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.board.dao.MemberDAOImpl;
import com.board.domain.MemberVO;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberDAOImpl dao;
	
	@InjectMocks
	MemberServiceImpl service;
	
	@Test
	public void loginTest() throws Exception {
		//given
		MemberVO vo = MemberVO.builder()
				.userId("JUnitTest")
				.userPass("1234")
				.userName("JUnitTest")
				.regDate(null).build();
		
		when(dao.login(any())).thenReturn(vo); // Mock 객체 주입
		
		//when
		MemberVO result = service.login(MemberVO.builder().userId("JUnitTest").build());
		
		//then
		verify(dao, times(1)).login(any());
		
	}

}
