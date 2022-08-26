package com.board.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskSample {
	
	
	// 리턴 타입은  void와 Future<String> 두 가지로 작성 가능
	// @Async 사용 시 제약사항이 두가지가 있다
	// 첫 번째는 함수는 무조건 public 타입일 것
	// 두 번째는 같은 클래스 안에서 셀프 호출은 안된다는 것
	@Async("executorSample")
	public void executorSample() {
		
		System.out.println("==> Thread START");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("==> Thread END");
	}
	
	@Async("executorSample")
	public void executorSample2(String str) {
		
		System.out.println("==> Thread START");
		
		
		System.out.println("==> Thread END");
	}
	
}
