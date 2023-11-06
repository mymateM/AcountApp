package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.connect.accountApp.domain.user.domain.model.Bank;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBanksResponse {

    List<BankResponse> banks;

    public GetBanksResponse(List<Bank> bankList) {
        banks = bankList.stream().map(BankResponse::new).toList();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class BankResponse {

        private String bankImageUrl;
        private String bankName;
        private String bankCategory;

        public BankResponse(Bank bank) {
            this.bankImageUrl = bank.getBankImage();
            this.bankName = bank.getBankName();
            this.bankCategory = bank.name();
        }
    }





}
