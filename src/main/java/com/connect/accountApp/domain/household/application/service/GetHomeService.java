package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdHomeUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.GetHouseholdHomeCommand;
import com.connect.accountApp.domain.household.application.port.in.command.GetUserHomeCommand;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
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
    private final GetHouseholdPort getHouseholdPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;
    private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;

    @Override
    public GetHouseholdHomeCommand getHouseholdHome(String userEmail) {

        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Household household = userWithHousehold.getHousehold();

        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(household.getHouseholdId());

        // 현재 날짜에서 가장 가까운 정산일을 가져온다.
        LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), household.getHouseholdSettlementDayOfMonth());
        BigDecimal householdPreviousTotalExpense = getHouseholdPreviousTotalExpense(pastNearSettlementDate, household.getHouseholdId());

        BigDecimal householdNowTotalExpense = getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(
                        household.getHouseholdId(), pastNearSettlementDate, LocalDateTime.now()).divide(BigDecimal.valueOf(householdMembers.size()));

        int dDay = Period.between(LocalDate.now(), pastNearSettlementDate.toLocalDate().plusMonths(1)).getDays();

        return new GetHouseholdHomeCommand(household, householdNowTotalExpense, householdPreviousTotalExpense, dDay,
                pastNearSettlementDate.toLocalDate());
    }

    @Override
    public GetUserHomeCommand getUserHome(String userEmail) {
        return null;
    }



    /**
     * 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈을 반환하는 함수
     * @param pastNearSettlementDate : 가까운 정산일
     * @param householdId : 가구 아이디
     * @return int 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈
     */
    private BigDecimal getHouseholdPreviousTotalExpense(LocalDateTime pastNearSettlementDate, Long householdId) {
        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(householdId);
        LocalDateTime previousStartTime = pastNearSettlementDate.toLocalDate().atStartOfDay().minusMonths(1);
        LocalDateTime previousEndTime = LocalDate.now().atStartOfDay().plusDays(1).minusSeconds(1).minusMonths(1);

        BigDecimal householdTotalExpenseByDate = getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(
                householdId, previousStartTime, previousEndTime);

        return householdTotalExpenseByDate.divide(BigDecimal.valueOf(householdMembers.size()));
    }


    /**
     * 현재 날짜에서 가장 가까운 정산일을 가져오는 함수
     * @param date
     * @param householdSettlementDate
     * @return LocalDateTime : 현재와 가장 가까운 정산일
     */
    private LocalDateTime getPastNearSettlementDate(LocalDate date, Integer householdSettlementDate) {

        if (date.getDayOfMonth() >= householdSettlementDate) {
            return LocalDate.of(date.getYear(), date.getMonth(), householdSettlementDate).atStartOfDay();
        } else {
            // TODO : 30, 31 고려
            if(householdSettlementDate==31) {
                return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate-1).atStartOfDay();
            }
            return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate).atStartOfDay();
        }

    }
}
