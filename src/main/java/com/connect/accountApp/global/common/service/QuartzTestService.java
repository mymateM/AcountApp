package com.connect.accountApp.global.common.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.connect.accountApp.global.common.application.port.in.QuartzTestUseCase;
import com.connect.accountApp.global.common.domain.HelloJob;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuartzTestService implements QuartzTestUseCase {

  private final ApplicationContext applicationContext;

  @Override
  public String changeScheduleTime(int second) {
    try {
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler = schedulerFactory.getScheduler();

      Map<String, Object> map = new LinkedHashMap<>();
      map.put("applicationContext", applicationContext);
      JobDetail job = newJob(HelloJob.class)
          .withIdentity("helloJob1", "helloGroup1")
          .withDescription("simple hello job")
          .usingJobData(new JobDataMap(map))
          .build();

      Trigger trigger = newTrigger()
          .withIdentity("testTrigger", "testGroup")
          .startNow()
          .withSchedule(cronSchedule("0 0/" + second + " * 1/1 * ? *"))
          .build();

      scheduler.scheduleJob(job, trigger);
      scheduler.start();

    } catch (SchedulerException e) {

    }
    return "스케쥴러 시작";
  }
}
