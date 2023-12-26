package com.connect.accountApp.domain.bill.adapter.in.web.response;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
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
public class GetBillsResponse {

  @JsonProperty("bills")
  private List<BillResponse> billResponse;

  public GetBillsResponse(
      List<BillCommand> commands) {
    this.billResponse = commands.stream().map(BillResponse::new).toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class BillResponse {

    @JsonProperty("bill_id")
    private Long billId;
    @JsonProperty("bill_image_url")
    private String billImageUrl;
    @JsonProperty("bill_payment_date")
    private LocalDate billPaymentDate;
    @JsonProperty("bill_store")
    private String billStore;
    @JsonProperty("bill_payment_amount")
    private BigDecimal billPaymentAmount;

    public BillResponse(BillCommand command) {
      this.billId = command.getBillId();
      this.billImageUrl = command.getBillImageUrl();
      this.billPaymentDate = command.getBillPaymentDate();
      this.billStore = command.getBillStore();
      this.billPaymentAmount = command.getBillPaymentAmount().setScale(0, RoundingMode.FLOOR);
    }
  }
}
