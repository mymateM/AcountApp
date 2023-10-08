package com.connect.accountApp.global.config;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

  @Bean
  public SchedulerFactory schedulerFactory() {
    return new StdSchedulerFactory();
  }

}
