package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;

public interface FindHouseholdUserListPort {

  List<User> findHouseholdUserList(Household household);

  List<User> findHouseholdMembers(Long householdId);

  Long findHouseholdId(String userEmail);

}
