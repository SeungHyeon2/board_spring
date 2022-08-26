package com.board.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskEtc {

	@Async
	public void executorEtc(String str) {
		
		System.out.println("==> Thread START");
		
		System.out.println("==> Thread END");
	}
	
	@Async
	public void executorEtc2(String str) {
		
		System.out.println("==> Thread START");
		
		System.out.println("==> Thread END");
	}
}
