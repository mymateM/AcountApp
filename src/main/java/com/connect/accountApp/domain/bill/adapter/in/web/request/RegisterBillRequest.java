package com.connect.accountApp.domain.bill.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBillRequest {

  @JsonProperty("bill_payment_date")
  private LocalDate billPaymentDate;
  @JsonProperty("bill_image")
  private String billImage;
  @JsonProperty("bill_payment_amount")
  private BigDecimal billPaymentAmount;
  @JsonProperty("bill_store")
  private String billStore;
  @JsonProperty("bill_category_title")
  private String billCategoryTitle;
  @JsonProperty("bill_memo")
  private String billMemo;

  @JsonProperty("virtual_accounts")
  private List<VirtualAccountRequest> virtualAccountRequest;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class VirtualAccountRequest {

    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("account_number")
    private String accountNumber;

  }
}
