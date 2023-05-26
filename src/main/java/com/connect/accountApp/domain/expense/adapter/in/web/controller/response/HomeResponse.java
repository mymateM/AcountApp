package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;

import com.connect.accountApp.domain.expense.application.port.in.command.HomeCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MemberNowCommand;
import com.connect.accountApp.domain.household.application.port.in.command.HouseholdNowCommand;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeResponse {

  private User user;
  private List<User> room_mates;
  private Household household;

  public HomeResponse(HomeCommand command) {

    MemberNowCommand userNowCommand = command.getMembersNowCommand().getUserNowCommand();
    this.user = new User(userNowCommand);

    List<MemberNowCommand> roomMateNowCommands = command.getMembersNowCommand().getRoomMateNowCommands();
    this.room_mates = roomMateNowCommands.stream().map(User::new).toList();

    HouseholdNowCommand householdNowCommand = command.getHouseholdNowCommand();
    this.household = new Household(householdNowCommand);

  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class User {

    private Long user_id;
    private String user_name;
    private int user_by_now_budget_ratio;
    private int user_ratio;
    private String user_title_name;
    private String user_title_days;

    public User(MemberNowCommand command) {
      this.user_id = command.getUserId();
      this.user_name = command.getUserName();
      this.user_by_now_budget_ratio = command.getUserByNowBudgetRatio().intValue();
      this.user_ratio = command.getUserRatio();
//      this.user_title_name;
//      this.user_title_days = user_title_days;
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class Household {
    private Long household_id;
    private LocalDate household_settlement_date;
    private int household_settlement_d_day;
    private int household_by_previous_expense;
    private int household_by_now_budget_ratio;
    private int household_now_expense_diff;
    private Boolean household_budget_over_warn;

    public Household(HouseholdNowCommand command) {
      this.household_id = command.getHouseholdId();
      this.household_settlement_d_day = command.getHouseholdSettlementDDay();
      this.household_by_previous_expense = command.getHouseholdByPreviousExpense();
      this.household_by_now_budget_ratio = command.getHouseholdByNowExpense();
      this.household_now_expense_diff = command.getHouseholdNowExpenseDiff();
      this.household_budget_over_warn = command.isHouseholdBudgetOverWarn();
    }

  }



}
