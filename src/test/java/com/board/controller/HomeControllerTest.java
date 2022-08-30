package com.board.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations= {
//		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
//		"file:src/main/webapp/WEB-INF/spring/context-common.xml"})
//@Log4j
class HomeControllerTest {

	
	@Test
	void contextLoads() {
		
	}
	/*
	 * @Autowired HomeController homeController;
	 * 
	 * MockMvc mockMvc;
	 * 
	 * @BeforeEach public void before() { mockMvc =
	 * MockMvcBuilders.standaloneSetup(homeController) .addFilter(new
	 * CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true)) .build(); }
	 * 
	 * @Test void homeControllerTest() throws Exception{ mockMvc.perform(get("/"))
	 * .andExpect(MockMvcResultMatchers.status().isOk()) .andDo(print()); }
	 */

}
