package com.board.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.service.AccountService;
import com.board.service.SecretService;

@Controller
@RequestMapping(value="/userauth/*")
public class LoginController {

	@Inject
	private AccountService accountService;
	
	@Inject
	private SecretService secretService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin() {
		return "/board/login";
	}
	
	/**
	 * 회원가입 시 호출되는 메서드
	 * @param jsonMap HTTP 요청 몸체(JSON)을 Map으로 치환 
	 * @return entity 반환
	 */
	@RequestMapping(value="/login/signup", method=RequestMethod.POST)
	public ResponseEntity<String> postSignUp(@RequestBody Map<String, Object> jsonMap){
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>(accountService.registerAccount(jsonMap), HttpStatus.OK);
		return entity;
	}
	
	/**
	 * 로그인을 처리하는 메서드
	 * @param request HttpServletRequest 객체
	 * @param jsonMap HTTP 요청 몸체(JSON)을 Map으로 치환
	 * @return entity 반환
	 */
	@RequestMapping(value="/login/login", method=RequestMethod.POST)
	public ResponseEntity<String> postLogin(HttpServletRequest request, @RequestBody Map<String, Object> jsonMap){
		ResponseEntity<String> entity = null;
		String resultMsg= "fail";
		if(accountService.checkLogin((String)jsonMap.get("inputId"), (String)jsonMap.get("inputPw"))){
			request.getSession().setAttribute("loginId", (String)jsonMap.get("inputId"));
			request.getSession().setAttribute("tokenStr", secretService.createToken((String)jsonMap.get("inputId")));
			resultMsg="success";
		}
		entity = new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		return entity;
	}
	
	
	/**
	 * 로그아웃 메서드
	 * @param request HttpServletRequest 객체 
	 * @return entity 반환
	 */
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<String> postLogout(HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		
		//세션을 삭제해준다.
		request.removeAttribute("loginId");
		request.removeAttribute("tokenStr");
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK); 
		return entity;
	}
}
