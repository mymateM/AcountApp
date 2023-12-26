package com.connect.accountApp.domain.bill.application.service;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.STORE_BILL;

import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;
import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest.VirtualAccountRequest;
import com.connect.accountApp.domain.bill.application.port.out.SaveBillPort;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.Bank;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.bill.application.port.in.RegisterBillUseCase;
import com.connect.accountApp.domain.usernotification.application.port.out.SaveUserActivityNotificationPort;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
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
  private final SaveVirtualAccountPort saveVirtualAccountPort;


  private final FindHouseholdUserListPort findHouseholdUserListPort;
  private final SaveActivityNotificationPort saveActivityNotificationPort;
  private final SaveUserActivityNotificationPort saveUserActivityNotificationPort;
  private final FindActivityNotificationsPort findActivityNotificationsPort;

  @Override
  public Long registerBill(String userEmail, RegisterBillRequest request) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(
        user.getHousehold().getHouseholdId());

    Bill newBill = createBill(user, request);
    Bill savedBill= saveBillPort.saveBill(newBill);

    List<VirtualAccount> virtualAccounts = createVirtualAccounts(request.getVirtualAccountRequest(), savedBill);
    saveVirtualAccountPort.saveAll(virtualAccounts);

    ActivityNotification activityNotification = createActivityNotification(savedBill);
    Long savedActivityNotificationId = saveActivityNotificationPort.saveActivityNotification(activityNotification);
    ActivityNotification savedActivityNotification =
        findActivityNotificationsPort.findActivityNotification(savedActivityNotificationId);

    List<UserActivityNotification> userActivityNotifications = userActivityNotifications(
        householdMembers, savedActivityNotification);
    saveUserActivityNotificationPort.saveUserActivityNotifications(userActivityNotifications);

    return savedBill.getBillId();
  }

  private Bill createBill(User user, RegisterBillRequest request) {

    return Bill.builder()
            .billPaymentDate(request.getBillPaymentDate())
            .billPayment(request.getBillPaymentAmount())
            .billStore(request.getBillStore())
            .billMemo(request.getBillMemo())
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

  private ActivityNotification createActivityNotification(Bill savedBill) {
    return ActivityNotification.builder()
        .activityNotificationCategory(STORE_BILL)
        .title(STORE_BILL.getTitle())
        .message("새로운 고지서를 확인하세요!")
        .isRead(false)
        .bill(savedBill)
        .build();
  }

  private List<UserActivityNotification> userActivityNotifications(
      List<User> householdMembers,
      ActivityNotification activityNotification) {

    return householdMembers.stream()
        .map(householdMember -> createUserActivityNotification(householdMember, activityNotification))
        .toList();
  }

  private UserActivityNotification createUserActivityNotification(User user, ActivityNotification activityNotification) {

    return UserActivityNotification.builder()
        .user(user)
        .activityNotification(activityNotification)
        .build();
  }
}
