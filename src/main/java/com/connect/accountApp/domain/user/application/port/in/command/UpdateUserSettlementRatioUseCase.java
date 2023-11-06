package com.connect.accountApp.domain.user.application.port.in.command;

import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserSettlementRequest;

public interface UpdateUserSettlementRatioUseCase {

    void updateUserSettlementRatio(String userEmail, UpdateUserSettlementRequest request);

}
