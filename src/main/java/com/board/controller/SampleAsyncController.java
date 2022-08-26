package com.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.config.AsyncConfig;
import com.board.service.AsyncTaskEtc;
import com.board.service.AsyncTaskSample;

@Controller
public class SampleAsyncController {
	
	// 시뮬레이션 스레드
	@Resource(name = "asyncTaskSample")
	private AsyncTaskSample asyncTaskSample;
	
	// 기타 스레드
	@Resource(name = "asyncTaskEtc")
	private AsyncTaskEtc asyncTaskEtc;
	
	// AsyncConfig
	@Resource(name = "asyncConfig")
	private AsyncConfig asyncConfig;

	
	
	@RequestMapping("/sample/sampleTask.do")
	public ModelAndView doTask(HttpServletRequest requet, HttpServletResponse response) throws Exception{
		//////////////////////////////////////////////////////
		// 스레드 생성
		//////////////////////////////////////////////////////
		for(int i=0; i<100; i++) {
			try {
				if(asyncConfig.isSampleTaskExecute()) {
					asyncTaskSample.executorSample();
				}else {
					System.out.println("==>> THREAD 개수 초과");
				}
			} catch(TaskRejectedException e) {
				System.out.println("==>> THREAD ERROR");
				System.out.println("TaskRejectedException : 등록 개수 초과");
				System.out.println("==>> THREAD END");
			}
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sample/sampleTask");
		return mav;
	}
	
	@RequestMapping("/sample/sampleTask2.do")
	public ModelAndView doTask2(HttpServletRequest request, HttpServletResponse response) {
		//////////////////////////////////////////////////////
		// 스레드 생성
		//////////////////////////////////////////////////////
		
		try {
			
			List<String> strList = new ArrayList<String>();
			strList.add("A");
			strList.add("B");
			strList.add("C");
			strList.add("D");
			
			if(asyncConfig.isEtcTaskExecute(strList.size())) {
				for(int i=0; i<strList.size(); i++) {
					asyncTaskEtc.executorEtc(strList.get(i));
				}
			}else {
				System.out.println("==>> M > Thread 개수 초과");
			}
		}catch(TaskRejectedException e) {
			System.out.println("==>> M > THREAD ERROR");
			System.out.println("TaskRejectedException : 등록 개수 초과");
			System.out.println("==>> M > THREAD END");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sample/sampleTask");
		return mav;
	}
}
