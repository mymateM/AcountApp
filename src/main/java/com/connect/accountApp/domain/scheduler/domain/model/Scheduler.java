package com.connect.accountApp.domain.scheduler.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;

public class Scheduler {

  private Long schedulerId;
  private String schedulerName;
  private String cron;
  private String executionYn;
  private String executionIp;
  private LocalDateTime lastChgDt;

  @Builder
  public Scheduler(Long schedulerId, String schedulerName, String cron,
      String executionYn, String executionIp, LocalDateTime lastChgDt) {
    this.schedulerId = schedulerId;
    this.schedulerName = schedulerName;
    this.cron = cron;
    this.executionYn = executionYn;
    this.executionIp = executionIp;
    this.lastChgDt = lastChgDt;
  }
}
