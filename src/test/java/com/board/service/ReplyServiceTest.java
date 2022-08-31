package com.board.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.board.dao.ReplyDAOImpl;
import com.board.domain.ReplyVO;

@ExtendWith(MockitoExtension.class)
class ReplyServiceTest {
	
	@InjectMocks
	ReplyServiceImpl replyServiceImpl;
	
	@Mock
	private ReplyDAOImpl dao;
	
	@Test
	public void createTest() {
		Assertions.assertThrows(RuntimeException.class, ()->{
			replyServiceImpl.write(null);
		});
		
	}
}
