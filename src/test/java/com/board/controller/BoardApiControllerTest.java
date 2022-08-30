package com.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.log4j.Log4j;



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
	
	@Autowired
	BoardApiController boardApiController;
	
	@Test
	public void shouldDoSomething() {
		list.add(100);
	}
	
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
		mockMvc.perform(get("/board/api")).andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print());
		
	}
	


}
