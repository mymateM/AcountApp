package com.connect.accountApp.domain.bill.adapter.in.web.response;

import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentBillCategoriesResponse {

    @JsonProperty("recent_bill_category")
    private List<RecentBillCategoryResponse> recentBillCategories;

    public RecentBillCategoriesResponse(List<RecentBillCategoryCommand> recentBillCategories) {
        this.recentBillCategories =
                recentBillCategories.stream().map(RecentBillCategoryResponse::new).toList();
    }

    public class RecentBillCategoryResponse {
        @JsonProperty("bill_category")
        private String billCategory;

        @JsonProperty("bill_payment_date")
        private LocalDate billPaymentDate;

        @JsonProperty("bill_payment_amount")
        private BigDecimal billPaymentAmount;

        public RecentBillCategoryResponse(RecentBillCategoryCommand command) {
            this.billCategory = command.getBillCategory().getTitle();
            this.billPaymentDate = command.getBillPaymentDate();
            this.billPaymentAmount = command.getBillPaymentAmount().setScale(0, RoundingMode.FLOOR);
        }
    }
}
