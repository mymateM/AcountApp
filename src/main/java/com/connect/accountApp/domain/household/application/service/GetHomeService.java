package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdHomeUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.GetHouseholdHomeCommand;
import com.connect.accountApp.domain.household.application.port.in.command.GetUserHomeCommand;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHomeService implements GetHouseholdHomeUseCase {

    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;
    private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;
    private final FindSettlementPort findSettlementPort;

    @Override
    public GetHouseholdHomeCommand getHouseholdHome(String userEmail) {

        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Household household = userWithHousehold.getHousehold();

        // 현재 날짜에서 가장 가까운 정산일을 가져온다. // 10 24
        LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.of(2023, 11, 23),
                household.getHouseholdSettlementDayOfMonth());

        BigDecimal householdPreviousTotalExpense = getHouseholdPreviousTotalExpense(pastNearSettlementDate,
                household.getHouseholdId()); // 이전 몇일간 사용한 금액

        System.out.println("householdPreviousTotalExpense = " + householdPreviousTotalExpense);

//        BigDecimal householdNowTotalExpense = // 지금 몇일간 사용한 금액
//                getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(household.getHouseholdId(),
//                        pastNearSettlementDate, LocalDateTime.now());
        BigDecimal householdNowTotalExpense = // 지금 몇일간 사용한 금액
                getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(household.getHouseholdId(),
                        pastNearSettlementDate, pastNearSettlementDate.toLocalDate().plusMonths(1).atStartOfDay());

        System.out.println("householdNowTotalExpense = " + householdNowTotalExpense);

//        int dDay = Period.between(LocalDate.now(), pastNearSettlementDate.toLocalDate().plusMonths(1)).getDays();
        int dDay = Period.between(pastNearSettlementDate.toLocalDate().plusMonths(1),
                LocalDate.of(2023, 11, 23)).getDays() + 1;

        return new GetHouseholdHomeCommand(household, householdNowTotalExpense, householdPreviousTotalExpense, dDay,
                pastNearSettlementDate.toLocalDate());
    }

    @Override
    public GetUserHomeCommand getUserHome(String userEmail) {

        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Household household = userWithHousehold.getHousehold();

        BigDecimal householdBudget = household.getHouseholdBudget();
        BigDecimal userRatio = BigDecimal.valueOf(userWithHousehold.getUserRatio());

        BigDecimal userTotalBudget = householdBudget.multiply(userRatio.divide(BigDecimal.valueOf(100)));
//
//        LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(),
//                household.getHouseholdSettlementDayOfMonth());
        LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.of(2023, 11, 23),
                household.getHouseholdSettlementDayOfMonth());

//        BigDecimal userByNowTotalExpense = findSettlementPort.findUserRealExpense(userWithHousehold.getUserId(),
//                pastNearSettlementDate.toLocalDate(), LocalDate.now());
        BigDecimal userByNowTotalExpense = findSettlementPort.findUserRealExpense(userWithHousehold.getUserId(),
                pastNearSettlementDate.toLocalDate(), LocalDate.of(2023, 11, 23));
//        BigDecimal userByNowTotalExpense =
//                getHouseholdTotalExpensePort.getUserTotalExpenseByDate(userWithHousehold.getUserId(),
//                        pastNearSettlementDate, LocalDateTime.now());

        return new GetUserHomeCommand(userWithHousehold.getUserId(), userTotalBudget, userByNowTotalExpense);
    }


    /**
     * 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈을 반환하는 함수
     *
     * @param pastNearSettlementDate : 가까운 정산일
     * @param householdId            : 가구 아이디
     * @return int 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈
     */
    private BigDecimal getHouseholdPreviousTotalExpense(LocalDateTime pastNearSettlementDate,
                                                        Long householdId) { // 10월 23일 -> 9월 24일부터 10월 23일까지
        System.out.println("pastNearSettlementDate = " + pastNearSettlementDate);
        LocalDateTime previousStartTime = pastNearSettlementDate.toLocalDate().atStartOfDay().minusMonths(1);
        System.out.println("previousStartTime = " + previousStartTime);
        LocalDateTime previousEndTime = LocalDate.of(2023, 11, 22).atStartOfDay().plusDays(1).minusSeconds(1)
                .minusMonths(1);
        System.out.println("previousEndTime = " + previousEndTime);
        return getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(householdId, previousStartTime,
                previousEndTime);
    }


    /**
     * 현재 날짜에서 가장 가까운 정산일을 가져오는 함수
     *
     * @param date
     * @param householdSettlementDate
     * @return LocalDateTime : 현재와 가장 가까운 정산일
     */
    private LocalDateTime getPastNearSettlementDate(LocalDate date, Integer householdSettlementDate) {

        //23 >= 23
        if (date.getDayOfMonth() > householdSettlementDate) {
            return LocalDate.of(date.getYear(), date.getMonth(), householdSettlementDate).atStartOfDay();
        } else if (date.getDayOfMonth() == householdSettlementDate) {
            return LocalDate.of(date.getYear(), date.minusMonths(1).getMonth(), householdSettlementDate + 1)
                    .atStartOfDay();
        } else {

            // TODO : 30, 31 고려
            if (householdSettlementDate == 31) {
                return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(),
                        householdSettlementDate - 1).atStartOfDay();
            }
            return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate)
                    .atStartOfDay();
        }

    }
}
