package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.connect.accountApp.domain.user.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
    @JsonProperty("account_image_url")
    private String accountImageUrl;
    @JsonProperty("members")
    private List<MemberAccountResponse> memberAccounts;

    public UserAccountResponse(User user, List<User> members) {
        this.accountBank = user.getUserAccountBank().getBankName();
        this.accountNumber = user.getUserAccount();
        this.accountImageUrl = user.getUserAccountBank().getBankImage();
        List<User> filteredMembers = members.stream().filter(member -> !member.getUserId().equals(user.getUserId())).toList();
        this.memberAccounts = filteredMembers.stream().map(MemberAccountResponse::new).toList();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class MemberAccountResponse {

        @JsonProperty("user_name")
        private String userName;
        @JsonProperty("user_profile_image")
        private String profileImage;
        @JsonProperty("account_bank")
        private String accountBank;
        @JsonProperty("account_number")
        private String accountNumber;

        public MemberAccountResponse(User user) {
            this.userName = user.getUserNickname();
            this.profileImage = user.getUserImgUrl();
            this.accountBank = user.getUserAccountBank().getBankName();
            this.accountNumber = user.getUserAccount();
        }
    }

}
