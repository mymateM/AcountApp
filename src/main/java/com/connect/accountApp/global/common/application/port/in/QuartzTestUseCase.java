package com.connect.accountApp.global.common.application.port.in;

public interface QuartzTestUseCase {

  String changeScheduleTime(int second);

  void initSchedule(Integer dayOfMonth, Long householdId);

}
