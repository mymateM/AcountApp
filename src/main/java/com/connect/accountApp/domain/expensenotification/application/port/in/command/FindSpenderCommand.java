package com.connect.accountApp.domain.expensenotification.application.port.in.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindSpenderCommand {

  private Long expenseId;
  private String spenderName;

  public FindSpenderCommand(Long expenseId, String spenderName) {
    this.expenseId = expenseId;
    this.spenderName = spenderName;
  }
}
