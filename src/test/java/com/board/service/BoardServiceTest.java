package com.board.service;

import static org.assertj.core.api.Assertions.assertThat;


import javax.inject.Inject;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.board.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/context-common.xml"})
@Log4j
class BoardServiceTest {

//	@Inject
//	private BoardServiceImpl boardServiceImpl;
	
	private static final int testIntValue = 1;
	private static final String testTitle = "testTitle";
	private static final String testWriter = "testWriter";
	private static final String testContent = "testContent";
	
	
	@Test
	@Order(1)
	void viewTest() throws Exception{
		
	}
//	
//	@Test
//	@Order(2)
//	void writeTest() throws Exception{
////		BoardVO vo = new BoardVO();
////		vo.setBno(testIntValue);
////		vo.setContent(testContent);
////		vo.setWriter(testWriter);
////		vo.setContent(testContent);
//		
//	}
//	
//	@Test
//	@Order(3)
//	void countTest() {
//		
//	}

}
