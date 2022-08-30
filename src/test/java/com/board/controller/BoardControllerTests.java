package com.board.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.board.service.BoardServiceImpl;

import lombok.extern.log4j.Log4j;



@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/context-common.xml"})
@Log4j
public class BoardControllerTests {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BoardServiceImpl boardService;
	
	@InjectMocks
	private BoardController boardController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		
	}



	


}
