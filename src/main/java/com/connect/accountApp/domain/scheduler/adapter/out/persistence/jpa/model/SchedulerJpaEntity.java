package com.connect.accountApp.domain.scheduler.adapter.out.persistence.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "scheduler")
public class SchedulerJpaEntity {

  @Id @GeneratedValue
  private Long schedulerId;
  private String schedulerName;

  @Column(length = 50, nullable = false)
  private String cron;

  @Column(name = "execution_yn", length = 1, nullable = false)
  private String executionYn;
  @Column(name = "execution_ip", length = 20, nullable = false)
  private String executionIp;
  @Column(name = "last_chg_dt")
  private LocalDateTime lastChgDt;

  @Builder
  public SchedulerJpaEntity(Long schedulerId, String schedulerName, String cron,
      String executionYn, String executionIp, LocalDateTime lastChgDt) {
    this.schedulerId = schedulerId;
    this.schedulerName = schedulerName;
    this.cron = cron;
    this.executionYn = executionYn;
    this.executionIp = executionIp;
    this.lastChgDt = lastChgDt;
  }

  //private User user;

  /*
  scheduler_idx : Scheduler를 식별하기 위한 index
  scheduler_name : Scheduler의 이름 (index와 매핑)
  cron : Scheduler 실행 주기를 결정할 cron식
  execution_yn : AP 내부에서의 Scheduler 실행여부를 결정
  execution_ip : Scheduler가 실행될 서버의 ip
  last_chg_dt : 설정 최종변경 일시
   */


}
