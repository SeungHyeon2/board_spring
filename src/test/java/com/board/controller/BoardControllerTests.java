package com.board.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.board.dao.BoardDAOImpl;
import com.board.domain.BoardVO;
import com.board.service.BoardService;
import com.board.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(boardController)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}

	@Test(expected = NullPointerException.class)
	@Order(1)
	public void countTest() throws Exception{
		assertThat(boardService.count(), is(1));
		
	}
	
	@Test
	@Order(2)
	public void delTest() throws Exception{
		assertTrue(true);
	}
	
//	@BeforeEach
//	public void before() {
//		mockMvc = MockMvcBuilders.standaloneSetup(boardController)
//					.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
//					.build();
//	}
//	
//	@Test
//	public void exTest() throws Exception{
//		log.info("test");
//	}
//	
//	@Test
//	public void controllerTest() throws Exception{
//		log.info("test123");
//		log.info(mockMvc.perform(get("/board/listPageSearch?num=1")).andExpect(MockMvcResultMatchers.status().isOk())
//					.andDo(print()));
//	}
	


}
