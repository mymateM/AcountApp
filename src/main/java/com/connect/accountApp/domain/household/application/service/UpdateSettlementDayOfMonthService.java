package com.connect.accountApp.domain.household.application.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.connect.accountApp.domain.household.application.port.in.UpdateSettlementDayOfMonthUseCase;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSettlementDayOfMonthService implements UpdateSettlementDayOfMonthUseCase {

  private final GetUserPort getUserPort;
  private final SaveHouseholdPort saveHouseholdPort;

  private final SchedulerFactory schedulerFactory;

  @Override
  public void updateSettlementDayOfMonth(String userEmail, int newSettlementDayOfMonth) {

    User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
    Household household = userWithHousehold.getHousehold();

    household.updateHouseholdSettlementDayOfMonth(newSettlementDayOfMonth);
    saveHouseholdPort.saveHousehold(household);

    try {
      Scheduler scheduler = schedulerFactory.getScheduler();
      Trigger oldTrigger = scheduler.getTrigger(
          TriggerKey.triggerKey("notifySettlementTrigger", "notifyTriggerGroup"));


      Trigger newTrigger = newTrigger()
          .withIdentity("notifySettlementTrigger", "notifyTriggerGroup")
          .startNow()
          .withSchedule(cronSchedule("0 0 10 " + newSettlementDayOfMonth + " 1/1 ? *"))
          .build();

      scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);

    } catch (SchedulerException e) {
      e.printStackTrace();
    }


  }

}
