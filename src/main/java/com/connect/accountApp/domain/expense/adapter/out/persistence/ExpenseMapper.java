package com.connect.accountApp.domain.expense.adapter.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {

  private final UserMapper userMapper;
  private final HouseholdMapper householdMapper;

  public Expense mapToDomainEntity(ExpenseJpaEntity expenseJpaEntity) {
    return Expense.builder()
            .expenseId(expenseJpaEntity.getExpenseId())
            .expenseAmount(expenseJpaEntity.getExpenseAmount())
            .expenseDate(expenseJpaEntity.getExpenseDate())
            .expenseStore(expenseJpaEntity.getExpenseStore())
            .expenseMemo(expenseJpaEntity.getExpenseMemo())
            .expenseCategory(expenseJpaEntity.getExpenseCategory())
            .spender(getUserOfExpenseJpaEntity(expenseJpaEntity.getSpender()))
            .household(getHouseHoldOfExpenseJpaEntity(expenseJpaEntity.getHouseHoldJpaEntity()))
            .build();
  }

  public ExpenseJpaEntity mapToJpaEntity(Expense expense) {
    return ExpenseJpaEntity.builder()
            .expenseId(expense.getExpenseId())
            .expenseAmount(expense.getExpenseAmount())
            .expenseDate(expense.getExpenseDate())
            .expenseStore(expense.getExpenseStore())
            .expenseMemo(expense.getExpenseMemo())
            .expenseCategory(expense.getExpenseCategory())
            .spender(getUserJpaEntityOfExpense(expense.getSpender()))
            .houseHoldJpaEntity(getHouseHoldJpaEntityOfExpense(expense.getHousehold()))
            .build();

  }


  private HouseHoldJpaEntity getHouseHoldJpaEntityOfExpense(Household household) {
    if (household == null) {
      return null;
    }else {
      return householdMapper.mapToJpaEntity(household);
    }
  }

  private Household getHouseHoldOfExpenseJpaEntity(HouseHoldJpaEntity houseHoldJpaEntity) {

    if (houseHoldJpaEntity == null || houseHoldJpaEntity instanceof HibernateProxy) {
      return null;
    }else {
      return householdMapper.mapToDomainEntity(houseHoldJpaEntity);
    }
  }


  private UserJpaEntity getUserJpaEntityOfExpense(User user) {
    if (user == null) {
      return null;
    }else {
      return userMapper.mapToJpaEntity(user);
    }
  }

  private User getUserOfExpenseJpaEntity(UserJpaEntity userJpaEntity) {

    if (userJpaEntity == null || userJpaEntity instanceof HibernateProxy) {
      return null;
    }else {
      return userMapper.mapToDomainEntity(userJpaEntity);
    }
  }


}
