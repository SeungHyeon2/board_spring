package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.MemberVO;
import com.board.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	// 회원가입(get)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception{
		logger.info("get register");
	}
	
	// 회원가입(post)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception{
		logger.info("post register");
		
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);
		
		service.register(vo);
		return "redirect:/";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		logger.info("vo", vo);
		HttpSession session = req.getSession();
		 
		MemberVO login = service.login(vo);
		
		if(service.idCheck(vo.getUserId()) == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/";
		}
		
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
		 
		if(login != null && passMatch) {
			session.setAttribute("member", login);
		}else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/";
		}
		
		
		/*
		 * if(login == null) { session.setAttribute("member", null);
		 * rttr.addFlashAttribute("msg", false); } else { session.setAttribute("member",
		 * login); }
		 */
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("get logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원정보 수정 get
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void getModify() throws Exception{
		logger.info("get modify");
	}
	
	// 회원정보 수정 post
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String postModify(HttpSession session, MemberVO vo) throws Exception{
		logger.info("post modify");
		
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);
		
		service.modify(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 탈퇴 get
	@RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
	public void getWithdrawal() throws Exception{
		logger.info("get withdrawal");
		
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
	public String postWithdrawal(HttpSession session, MemberVO vo, RedirectAttributes rttr) throws Exception{
		logger.info("post withdrawal");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		// 로그인된 member의 password를 oldPass에 저장
		String oldPass = member.getUserPass();
		
		// input에서 넘어온 password를 newPass에 저장
		String newPass = vo.getUserPass();
		
		//String newPass = passEncoder.encode(vo.getUserPass());
		
		// 로그인된 member의 값(인코딩 된)과 input에서 넘어온 password를 비교
		boolean passMatch = passEncoder.matches(newPass, oldPass);
		
		// 일치하지 않으면 현재 페이지로 리다이렉트
		if(!(passMatch)) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/withdrawal";
		}
		
		/*
		 * if(!(oldPass.equals(newPass))) { rttr.addFlashAttribute("msg", false); return
		 * "redirect:/member/withdrawal"; }
		 */
		
		service.withdrawal(vo);
		
		session.invalidate();
		
		return "redirect:/";
		//return "redirect:/board/listPageSearch?num=1";
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method=RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception{
		logger.info("post idCheck");
		
		String userId = req.getParameter("userId");
		MemberVO idCheck = service.idCheck(userId);
		
		int result = 0;
		
		if(idCheck != null) {
			result = 1;
		}
		
		return result;
	}
}
