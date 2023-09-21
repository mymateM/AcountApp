package com.connect.accountApp.settlement.application.port.out;

import com.connect.accountApp.settlement.domain.model.Settlement;

public interface SaveSettlementPort {

  Long saveSettlement(Settlement settlement);

}
