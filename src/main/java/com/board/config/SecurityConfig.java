//package com.board.config;
//
//import javax.servlet.Filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.board.auth.JwtAuthenticationFilter;
//import com.board.auth.JwtAuthenticationManager;
//
//@Configuration
//public class SecurityConfig {
//
//	@Autowired
//	private JwtAuthenticationManager jwtAuthenticationManager;
//
//	@Bean
//	public Filter jwtAuthenticationFilter() {
//		JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
//		filter.setAuthenticationManager(jwtAuthenticationManager);
//		// We do not need to do anything extra on REST authentication success, because there is no page to redirect to
//		filter.setAuthenticationSuccessHandler((request, response, authentication) -> {});        return filter;
//		
//	}
//}
