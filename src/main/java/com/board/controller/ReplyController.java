package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.ReplyVO;
import com.board.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ReplyService replyService;
	
	// 댓글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(ReplyVO vo) throws Exception {
		logger.info("ReplyController postWrite 메소드 동작");
		
		replyService.write(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	// 대댓글 작성
	//getBno를 하면 0, 즉 null값이 들어가있어서 return 오류가 발생
	@RequestMapping(value = "/reWrite", method = RequestMethod.POST)
	public String reWrite(ReplyVO vo) throws Exception{
		logger.info("ReplyController reWrite 메소드 동작");
		replyService.reWrite(vo);
		logger.info("ReplyController reWrite 메소드 끝");
		System.out.println(vo.getRef());
		return "redirect:/board/view?bno=" + vo.getBno();
	}
}
