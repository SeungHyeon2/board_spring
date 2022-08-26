package com.board.config;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.board.exception.AsyncExceptionHandler;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
	
	// 샘플 기본 Thread 수
	private static int TASK_SAMPLE_CORE_POOL_SIZE = 5;
	
	// 샘플 최대 Thread 수
	private static int TASK_SAMPLE_MAX_POOL_SIZE = 100;
	
	// 샘플 QUEUE 수
	private static int TASK_SAMPLE_QUEUE_CAPACITY = 5;
	
	// 샘플 Thread Bean Name
	private static String EXECUTOR_SAMPLE_BEAN_NAME = "executorSample";
	
	// 샘플 Thread
	@Resource(name = "executorSample")
	private ThreadPoolTaskExecutor executorSample;
	
	
	@Bean(name = "executorSample")
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(TASK_SAMPLE_CORE_POOL_SIZE);
		executor.setMaxPoolSize(TASK_SAMPLE_MAX_POOL_SIZE);
		executor.setQueueCapacity(TASK_SAMPLE_QUEUE_CAPACITY);
		executor.setBeanName(EXECUTOR_SAMPLE_BEAN_NAME);
		
		executor.initialize();
		return executor;
	}
	
	
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}
	
	// 샘플 Thread 등록 가능 여부
	// @return 실행중인 task 개수가 최대 개수(max + queue)보다 크거나 같으면 false
	public boolean isSampleTaskExecute() {
		boolean rtn = true;
		
		System.out.println("EXECUTOR_SAMPLE.getActiveCount()" + executorSample.getActiveCount());
		
		// 실행 중인 task 개수가 최대 개수(max + queue)보다 크거나 같으면 false
		if(executorSample.getActiveCount() >= (TASK_SAMPLE_MAX_POOL_SIZE + TASK_SAMPLE_QUEUE_CAPACITY)) {
			rtn = false;
		}
		
		return rtn;
	}
	
	// @Param createcnt : 생성 개수
	// @return 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크면 false
	public boolean isSampleTaskExecute(int createCnt) {
		boolean rtn = true;
		
		// 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크면 false
		if((executorSample.getActiveCount() + createCnt) > (TASK_SAMPLE_MAX_POOL_SIZE + TASK_SAMPLE_QUEUE_CAPACITY)) {
			rtn = false;
		}
		
		return rtn;
	}
	
	///////////////////////////////////////////////
	////// Executor 다중 생성

	// 기타 기본 Thread 수
	private static int TASK_ETC_CORE_POOL_SIZE = 5;
	
	// 기타 최대 Thread 수
	private static int TASK_ETC_MAX_POOL_SIZE = 10;
	
	// 기타 QUEUE 수
	private static int TASK_ETC_QUEUE_CAPACITY = 0;
	
	// 기타 Thread Bean Name
	private static String EXECUTOR_ETC_BEAN_NAME = "executorEtc";
	
	// 기타 Thread
	@Resource(name = "executorEtc")
	private ThreadPoolTaskExecutor executorEtc;
	
	@Bean(name = "executorEtc")
	@Qualifier
	public Executor taskExecutorEtc() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(TASK_ETC_CORE_POOL_SIZE);
		executor.setMaxPoolSize(TASK_ETC_MAX_POOL_SIZE);
		executor.setQueueCapacity(TASK_ETC_QUEUE_CAPACITY);
		executor.setBeanName(EXECUTOR_ETC_BEAN_NAME);
		executor.initialize();
		return executor;
	}
	
	public boolean isEtcTaskExecute() {
		boolean rtn = true;
		
		if(executorEtc.getActiveCount() >= TASK_ETC_MAX_POOL_SIZE + TASK_ETC_QUEUE_CAPACITY) {
			rtn = false;
		}
		return rtn;
	}
	
	public boolean isEtcTaskExecute(int createCnt) {
		boolean rtn = true;
		
		if((executorEtc.getActiveCount() + createCnt) > (TASK_ETC_MAX_POOL_SIZE + TASK_ETC_QUEUE_CAPACITY)) {
			rtn = false;
		}
		
		return rtn;
	}
}
