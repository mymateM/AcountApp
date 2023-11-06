package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.connect.accountApp.domain.user.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccountResponse {

    @JsonProperty("account_bank")
    private String accountBank;
    @JsonProperty("account_number")
    private String accountNumber;

    public UserAccountResponse(User user) {
        this.accountBank = user.getUserAccountBank().getBankName();
        this.accountNumber = user.getUserAccount();
    }
}
