package com.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import com.board.domain.BoardVO;
import com.board.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/context-common.xml"})
@Log4j
public class BoardApiControllerTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Mock
	private List list;
	
	@Test
	public void shouldDoSomething() {
		list.add(100);
	}
	
	@Inject
	BoardApiController boardApiController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void before() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardApiController)
					.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
					.build();
	}
	
	@Test
	public void exTest() throws Exception{
		log.info("test");
	}
	
	@Test
	public void controllerTest() throws Exception{
		log.info("test123");
		mockMvc.perform(get("/api/board")).andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		
	}
	


}
