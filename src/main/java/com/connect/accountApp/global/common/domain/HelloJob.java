package com.connect.accountApp.global.common.domain;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

  public HelloJob() {
    System.err.println("HelloJob created");
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.err.println("Hello");
  }
}
