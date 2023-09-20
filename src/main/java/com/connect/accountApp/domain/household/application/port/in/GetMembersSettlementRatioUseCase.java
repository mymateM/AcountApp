package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.household.application.port.in.command.GetMembersSettlementCommand;
import java.util.List;

public interface GetMembersSettlementRatioUseCase {

  List<GetMembersSettlementCommand> getMembersSettlementRatio(String userEmail);

}
