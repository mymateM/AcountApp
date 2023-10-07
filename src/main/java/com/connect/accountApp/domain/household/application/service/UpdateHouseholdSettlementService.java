package com.connect.accountApp.domain.household.application.service;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.UPDATE_BUDGET;
import static java.math.RoundingMode.FLOOR;

import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.household.application.port.in.UpdateHouseholdSettlementUseCase;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHouseholdSettlementService implements UpdateHouseholdSettlementUseCase {

  private final SaveHouseholdPort saveHouseholdPort;
  private final GetUserPort getUserPort;
  private final SaveActivityNotificationPort saveActivityNotificationPort;

  @Override
  public BigDecimal updateHouseholdSettlement(String userEmail, BigDecimal budgetAmount) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    Household household = user.getHousehold();

    household.updateHouseholdBudget(budgetAmount);
    household.setSettlementWillBeUpdatedToTrue();
    saveHouseholdPort.saveHousehold(household);

    ActivityNotification activityNotification = createUpdateSettlementDateNotification(budgetAmount, user);
    saveActivityNotificationPort.saveActivityNotification(activityNotification);

    return household.getHouseholdBudget();
  }

  private ActivityNotification createUpdateSettlementDateNotification(BigDecimal newHouseholdBudget, User user) {
    return ActivityNotification.builder()
        .activityNotificationCategory(UPDATE_BUDGET)
        .title(UPDATE_BUDGET.getTitle())
        .message("다음 정산 때 예산이 " + newHouseholdBudget.setScale(0, FLOOR) + "원으로 바뀔 예정이에요!")
        .isRead(false)
        .requester(user)
        .build();
  }

}
