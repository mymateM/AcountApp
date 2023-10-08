package com.connect.accountApp.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfig {

  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(1);                        // Scheduler task 실행을 위한 thread의 갯수
    taskScheduler.setThreadNamePrefix("jobScheduler");    // thread 이름
    taskScheduler.initialize();                            // 초기화
    return taskScheduler;
  }
}
