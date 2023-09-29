package com.connect.accountApp.domain.settlement.application.port.out;

import com.connect.accountApp.domain.settlement.domain.model.Settlement;

public interface SaveSettlementPort {

  Long saveSettlement(Settlement settlement);

}
