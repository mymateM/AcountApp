package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;
import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest.VirtualAccountRequest;
import com.connect.accountApp.domain.bill.application.port.out.SaveBillPort;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.Bank;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.bill.application.port.in.RegisterBillUseCase;
import com.connect.accountApp.domain.virtualaccount.application.port.out.SaveVirtualAccountPort;
import com.connect.accountApp.domain.virtualaccount.domain.model.VirtualAccount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterBillService implements RegisterBillUseCase {

  private final GetUserPort getUserPort;
  private final SaveBillPort saveBillPort;
//  private final FindBillPort findBillPort;
  private final SaveVirtualAccountPort saveVirtualAccountPort;

  @Override
  public Long registerBill(String userEmail, RegisterBillRequest request) {

    User user = getUserPort.findUserWithHousehold(userEmail);

    Bill newBill = createBill(user, request);
    Bill savedBill= saveBillPort.saveBill(newBill);

    List<VirtualAccount> virtualAccounts = createVirtualAccounts(request.getVirtualAccountRequest(), savedBill);
    saveVirtualAccountPort.saveAll(virtualAccounts);

    return savedBill.getBillId();
  }

  private Bill createBill(User user, RegisterBillRequest request) {

    return Bill.builder()
        .billPaymentDate(request.getBillPaymentDate())
        .billPayment(request.getBillPaymentAmount())
        .billStore(request.getBillStore())
        .billCategory(BillCategory.valueOf(request.getBillCategoryTitle()))
        .billRegister(user)
        .household(user.getHousehold())
        .build();
  }

  private List<VirtualAccount> createVirtualAccounts(List<VirtualAccountRequest> requests, Bill savedBill) {
    return requests.stream().map(request -> createVirtualAccount(request, savedBill)).toList();
  }

  private VirtualAccount createVirtualAccount(VirtualAccountRequest request, Bill savedBill) {

    return VirtualAccount.builder()
        .bank(Bank.valueOf(request.getBankName()))
        .accountNumber(request.getAccountNumber())
        .bill(savedBill)
        .build();
  }
}
