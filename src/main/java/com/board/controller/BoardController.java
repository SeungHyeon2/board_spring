package com.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.config.AsyncConfig;
import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.domain.ReplyVO;
import com.board.service.AsyncTaskEtc;
import com.board.service.AsyncTaskSample;
import com.board.service.BoardService;
import com.board.service.ReplyService;


@Controller
//@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
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
	
	
	
//	@Inject
//	MemberService memberService;
//	
//	// 멤버 목록
//	public void memberList(Model model) throws Exception{
//		List<MemberVO> list = null;
//		list = memberService.list();
//		logger.info("member /list 호출");
//		logger.info(list.toString());
//		
//		model.addAttribute("memberList", list);
//	}
	
	
	
	// 게시물 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception{
		List<BoardVO> list = null;
		list = service.list();
		logger.info("/list 호출");
		logger.info(list.toString());
		model.addAttribute("list", list);
		
	}
	
	// 게시물 작성(GET)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite(HttpSession session, Model model) throws Exception{
		logger.info("get write");
		
		Object loginInfo = session.getAttribute("member");
		
		if(loginInfo == null) {
			model.addAttribute("msg", "login_error");
		}
	}
	
	// 게시물 작성(POST)
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception{
//		
		logger.info("title : " + vo.getTitle());
		logger.info("file : " + mpRequest.getContentLength());
//		
		logger.info("mpRequest=" + mpRequest); 
		service.write(vo, mpRequest);
		
		
		
		return "redirect:/board/listPageSearch?num=1";
	}
	
	// 게시물 조회
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVO vo = service.view(bno);
		
		model.addAttribute("view", vo);
		
		// 댓글 조회
		List<ReplyVO> reply = null;
		reply = replyService.list(bno);
		model.addAttribute("reply", reply);
		
		// 첨부파일 조회
		List<Map<String, Object>> fileList = service.selectFileList(vo.getBno());
		model.addAttribute("file", fileList);
	}
	
	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {

		BoardVO vo = service.view(bno);
	   
		model.addAttribute("view", vo);
	
		// 첨부파일 조회
		List<Map<String, Object>> fileList = service.selectFileList(vo.getBno());
		model.addAttribute("file", fileList);
	}
	
	//게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		service.modify(vo);
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
	  
		service.delete(bno);
		service.delYN(bno);
		
		return "redirect:/board/listPageSearch?num=1";
	}
	
	// 파일 다운로드
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = service.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("stored_file_name");
		String originalFileName = (String) resultMap.get("org_file_name");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("D:\\board\\board\\file\\"+storedFileName));
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	// 파일 수정
	@RequestMapping(value = "/fileModify", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileModify(@RequestParam("bno") int bno) throws Exception{
		service.modifyFile(bno);
		
		return "redirect:/board/modify?bno=" + bno;
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());		
		
		List<BoardVO> list = null; 
		list = service.listPage(page.getDisplayPost(), page.getPostNum());
		
		model.addAttribute("list", list);
		
		model.addAttribute("page", page);
		
		model.addAttribute("select", num);
		
	}
	
	// 게시물 목록 + 페이징 추가 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num, 
			@RequestParam(value = "searchType",required = false, defaultValue = "title") String searchType,
			@RequestParam(value = "keyword",required = false, defaultValue = "") String keyword
			) throws Exception {
	
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.searchCount(searchType, keyword));
		
		// 검색 타입과 검색어
		page.setSearchType(searchType);
		page.setKeyword(keyword);
				
		List<BoardVO> list = null; 
		list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
		logger.info("listPageSearch 실행");
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
	}
	

}
