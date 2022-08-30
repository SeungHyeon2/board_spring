package com.board.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.board.service.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private MemberService memberService;
	
	public void setUp(WebApplicationContext ctx) {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}
	
	@Test
	public void test() {
		
	}

}
