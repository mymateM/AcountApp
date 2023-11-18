package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.settlement.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.HouseholdSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.ReceiverCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.RoommateSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.SenderCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.SettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdSettlementService implements GetHouseholdSettlementUseCase {

    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;
    private final FindSettlementPort findSettlementPort;
    private final FindExpensePort findExpensePort;


    @Override
    public HouseholdSettlementCommand getRoommateSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

        User user = getUserPort.findUserWithHousehold(userEmail);
        BigDecimal householdTotalExpenses =
                findExpensePort.findHouseholdTotalExpenses(user.getHousehold().getHouseholdId(), startDate,
                        endDate); // 가구에서 전체 사용한 돈
        BigDecimal userRealExpense = findSettlementPort.findUserRealExpense(user.getUserId(), startDate,
                endDate); // 실제 지출한 돈

        BigDecimal userRatioExpense = householdTotalExpenses.multiply(
                BigDecimal.valueOf(user.getUserRatio() / 100.0)); // 지출해야 하는 돈(실제 가구가 사용한 돈에서)

        // 실제 지출한 돈 - 지출 해야하는 돈 -> 실제 지출한 돈이 더 크면, 즉 양수면 받아야지 => sender : false
        BigDecimal userSettlement = userRealExpense.subtract(userRatioExpense).abs();

        UserSettlementCommand userSettlementCommand = new UserSettlementCommand(user.getUserId(),
                user.getUserNickname(),
                userRealExpense, userRatioExpense);

        List<User> allMembers = findHouseholdUserListPort.findHouseholdUserList(user.getHousehold());
        List<User> members = allMembers.stream().filter(member -> !Objects.equals(member.getUserId(), user.getUserId()))
                .toList();

        int ratioSum = members.stream()
                .mapToInt(User::getUserRatio)
                .sum();

        System.out.println("ratioSum = " + ratioSum);

        List<RoommateSettlementCommand> roommateSettlementCommands = members.stream()
                .map(member -> new RoommateSettlementCommand(member.getUserNickname(), member.getUserId(),
                        userSettlement.multiply(BigDecimal.valueOf(member.getUserRatio() / (double) ratioSum)),
                        member.getUserAccountBank().getBankName(), member.getUserAccount())).toList();

        List<SettlementCommand> commands = test(user, startDate, endDate);
        //(String name, Long id, BigDecimal settlementAmount, String accountBank, String accountNumber)
        List<SettlementCommand> senderCommands = commands.stream()
                .filter(command -> command.getSenderId() == user.getUserId() || command.getGiverId() == user.getUserId()).toList();

        /*
            private Long giverId;
            private Long senderId;
            private BigDecimal settlementAmount;
         */

        /*
            private String name;
            private Long id;
            private BigDecimal settlementAmount;
            private String accountBank;
            private String accountNumber;
         */
        List<RoommateSettlementCommand> roommateSettlementCommands1;
        if (!userSettlementCommand.getIsSettlementSender()) {
            // 만약 user가 sender면
            roommateSettlementCommands1 = senderCommands.stream().map(command -> {
                User user1 = getUserPort.getUser(command.getGiverId());
                return new RoommateSettlementCommand(user1.getUserNickname(), user1.getUserId(),
                        command.getSettlementAmount(), user1.getUserAccountBank().getBankName(),
                        user1.getUserAccount());
            }).toList();
        } else {
            //giver라면
            roommateSettlementCommands1 = senderCommands.stream().map(command -> {
                User user1 = getUserPort.getUser(command.getSenderId());
                return new RoommateSettlementCommand(user1.getUserNickname(), user1.getUserId(),
                        command.getSettlementAmount(), user1.getUserAccountBank().getBankName(), user1.getUserAccount());
            }).toList();
        }





        return new HouseholdSettlementCommand(userSettlementCommand, roommateSettlementCommands1);
    }

    private List<SettlementCommand> test(User user, LocalDate startDate, LocalDate endDate) {

        Map<Long, BigDecimal> sender = new TreeMap<>();
        Map<Long, BigDecimal> receiver = new TreeMap<>();

        List<User> allMembers = findHouseholdUserListPort.findHouseholdUserList(user.getHousehold());

        List<User> users = allMembers.stream().map(member -> getUserPort.findUserWithHousehold(member.getUserEmail()))
                .toList();

        List<UserSettlementCommand> userSettlementCommands = users.stream()
                .map(member -> getUserSettlementCommand(member, startDate, endDate))
                .toList();

        userSettlementCommands.forEach(
                userSettlementCommand -> addSettlementMap(userSettlementCommand, sender, receiver));

        List<SenderCommand> senderCommands = new ArrayList<>();
        List<ReceiverCommand> receiverCommands = new ArrayList<>();

        for (Long senderId : sender.keySet()) {
            BigDecimal settlementAmount = sender.get(senderId);
            senderCommands.add(new SenderCommand(senderId, settlementAmount));
        }

        for (Long receiverId : receiver.keySet()) {
            BigDecimal settlementAmount = receiver.get(receiverId);
            receiverCommands.add(new ReceiverCommand(receiverId, settlementAmount));
        }


        Collections.sort(senderCommands, Comparator.comparing(SenderCommand::getSenderAmount).reversed());
        Collections.sort(receiverCommands, Comparator.comparing(ReceiverCommand::getReceiverAmount).reversed());

        // 위까지 정렬 끝!

        List<SettlementCommand> settlementCommands = new ArrayList<>();

        // 보내는 사람 입장이 제일 클 때

        int i = 0;
        int j = 0;
        while (i < senderCommands.size() && j < receiverCommands.size()) {

            SenderCommand senderCommand = senderCommands.get(i);
            BigDecimal senderAmount = senderCommand.getSenderAmount();

            ReceiverCommand receiverCommand = receiverCommands.get(j);
            BigDecimal receiverAmount = receiverCommand.getReceiverAmount();


            if (senderAmount.compareTo(receiverAmount) <= 0) {
                receiverCommand.setReceiverAmount(receiverAmount.subtract(senderAmount));// reciever는 빼준다.
                senderCommand.setSenderAmount(BigDecimal.ZERO);
                settlementCommands.add(
                        new SettlementCommand(senderCommand.getSenderId(), receiverCommand.getReceiverId(),
                                senderAmount));
            } else {
                senderCommand.setSenderAmount(senderAmount.subtract(receiverAmount));// sender에서 빼준다.
                receiverCommand.setReceiverAmount(BigDecimal.ZERO);
                settlementCommands.add(
                        new SettlementCommand(senderCommand.getSenderId(), receiverCommand.getReceiverId(),
                                receiverAmount));
            }

            if (senderCommand.getSenderAmount().equals(BigDecimal.ZERO)) {
                i++;
            }

            if (receiverCommand.getReceiverAmount().equals(BigDecimal.ZERO)) {
                j++;
            }

        }


        for (SettlementCommand command : settlementCommands) {
            System.out.println("command = " + command);
        }

        return settlementCommands;
    }

    private void addSettlementMap(UserSettlementCommand userSettlementCommand,
                                  Map<Long, BigDecimal> sender,
                                  Map<Long, BigDecimal> receiver) {
        if (userSettlementCommand.getIsSettlementSender()) {
            sender.put(userSettlementCommand.getId(), userSettlementCommand.getSettlementAmount());
        } else {
            receiver.put(userSettlementCommand.getId(), userSettlementCommand.getSettlementAmount());
        }
    }

    private UserSettlementCommand getUserSettlementCommand(User user, LocalDate startDate, LocalDate endDate) {
        BigDecimal householdTotalExpenses =
                findExpensePort.findHouseholdTotalExpenses(user.getHousehold().getHouseholdId(), startDate, endDate);
        BigDecimal userRatioExpense = householdTotalExpenses.multiply(BigDecimal.valueOf(user.getUserRatio() / 100.0));
        BigDecimal userRealExpense = findSettlementPort.findUserRealExpense(user.getUserId(), startDate,
                endDate); // 실제 지출한 돈
        return new UserSettlementCommand(user.getUserId(), user.getUserNickname(),
                userRealExpense, userRatioExpense);
    }

}
