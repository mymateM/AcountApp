package com.connect.accountApp.domain.virtualaccount.application.port.out;

import com.connect.accountApp.domain.virtualaccount.domain.model.VirtualAccount;
import java.util.List;

public interface SaveVirtualAccountPort {

  List<VirtualAccount> saveAll(List<VirtualAccount> newVirtualAccounts);

}
