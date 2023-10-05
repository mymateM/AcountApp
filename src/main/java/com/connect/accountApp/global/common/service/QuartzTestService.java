package com.connect.accountApp.global.common.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.connect.accountApp.global.common.application.port.in.QuartzTestUseCase;
import com.connect.accountApp.global.common.domain.HelloJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuartzTestService implements QuartzTestUseCase {


  @Override
  public String changeScheduleTime(int second) {
    try {
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();

      JobDetail job = newJob(HelloJob.class)
          .withIdentity("testJob", "testGroup")
          .withDescription("simple hello job")
          .build();

//      CronScheduleBuilder cronScheduleBuilder = cronSchedule("0 0/"+second+" * 1/1 * ? *");
      Trigger trigger = newTrigger()
          .withIdentity("testTrigger", "testGroup")
          .startNow()
          .withSchedule(cronSchedule("0 0/"+second+" * 1/1 * ? *"))
          .build();

      scheduler.scheduleJob(job, trigger);
      scheduler.start();

//      scheduler.shutdown();
    } catch (SchedulerException e) {

    }
    return "스케쥴러 시작";
  }
}
