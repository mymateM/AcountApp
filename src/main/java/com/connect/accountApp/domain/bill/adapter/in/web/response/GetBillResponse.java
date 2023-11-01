package com.connect.accountApp.domain.bill.adapter.in.web.response;

import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBillResponse {

    @JsonProperty("bill_category")
    private String billCategory;
    @JsonProperty("bill_payment_amount")
    private BigDecimal billPaymentAmount;
    @JsonProperty("bill_image_url")
    private String billImageUrl;
    @JsonProperty("bill_payment_date")
    private LocalDate billPaymentDate;
    @JsonProperty("bill_memo")
    private String billMemo;

    public GetBillResponse(Bill bill) {
        this.billCategory = bill.getBillCategory().getTitle();
        this.billPaymentAmount = bill.getBillPayment().setScale(0, RoundingMode.FLOOR);
        this.billImageUrl = bill.getBillCategory().getImgUrl();
        this.billPaymentDate = bill.getBillPaymentDate();
        this.billMemo = bill.getBillMemo();
    }
}
