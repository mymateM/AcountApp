package com.connect.accountApp.domain.household.application.port.in;

public interface GetHouseholdUseCase {

    String getHouseholdName(String userEmail);

    String getHouseholdInviteCode(String userEmail);

    Integer getHouseholdSettlementDayOfWeek(String userEmail);

}
