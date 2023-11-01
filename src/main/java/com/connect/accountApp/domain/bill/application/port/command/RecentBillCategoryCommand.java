package com.connect.accountApp.domain.bill.application.port.command;

import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentBillCategoryCommand {

    private BillCategory billCategory;
    private LocalDate billPaymentDate;
    private BigDecimal billPaymentAmount;

    public RecentBillCategoryCommand(BillCategory billCategory,
                                     LocalDate billPaymentDate,
                                     BigDecimal billPaymentAmount) {
        this.billCategory = billCategory;
        this.billPaymentDate = billPaymentDate;
        this.billPaymentAmount = billPaymentAmount;
    }
}
