package com.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.config.AsyncConfig;
import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;
import com.board.service.AsyncTaskEtc;
import com.board.service.AsyncTaskSample;
import com.board.service.BoardService;
import com.board.service.ReplyService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/board/api")
public class BoardApiController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
    
	// 게시판 서비스
	@Autowired
	BoardService service;
	
	// 게시판 답글 서비스
	@Autowired
	private ReplyService replyService;
	
	// 시뮬레이션 스레드
	@Resource(name = "asyncTaskSample")
	private AsyncTaskSample asyncTaskSample;
	
	// 기타 스레드
	@Resource(name = "asyncTaskEtc")
	private AsyncTaskEtc asyncTaskEtc;
	
	// AsyncConfig
	@Resource(name = "asyncConfig")
	private AsyncConfig asyncConfig;
	
	// json 테스트
	@GetMapping("/json")
		public Map<String, String> jsonTest(){
		Map<String, String> res = new HashMap<>();
		res.put("test", "hello");
		return res;
	}
	
	// 게시물 조회
	@RequestMapping(value="test", method = RequestMethod.GET)
	public void test() throws Exception{
		
	}
	
	// 게시물 조회
	@GetMapping("/list")
	public List<BoardVO> openBoardList(HttpServletRequest request) throws Exception{
		return service.list();
	}
	
	// 게시물 작성
	@PostMapping("/write")
	@ResponseBody
	public void insertBoard(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception{
		
		for(int i=0; i<1; i++) {
			try {
				if(asyncConfig.isSampleTaskExecute()) {
					service.write(vo, mpRequest);
				}else {
					System.out.println("==>> THREAD 개수 초과");
				}
			} catch(TaskRejectedException e) {
				System.out.println("==>> THREAD ERROR");
				System.out.println("TaskRejectedException : 등록 개수 초과");
				System.out.println("==>> THREAD END");
			}
		}
		
//		service.write(vo, mpRequest);
	}
	
	// 게시물 수정
	@RequestMapping(value="/modify/{bno}", method=RequestMethod.POST)
	public void updateBoard(BoardVO vo) throws Exception{
		service.modify(vo);
	
	}
	
	// 게시물 삭제
	@RequestMapping(value="/delete/{bno}", method=RequestMethod.GET)
	public void deleteBoard(@PathVariable("bno") int bno) throws Exception{
		
		service.delete(bno);
		service.delYN(bno);
		
	}
	


}