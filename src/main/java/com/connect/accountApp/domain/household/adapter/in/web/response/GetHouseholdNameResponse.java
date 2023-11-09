package com.connect.accountApp.domain.household.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdNameResponse {

    @JsonProperty("household_name")
    private String householdName;

    public GetHouseholdNameResponse(String householdName) {
        this.householdName = householdName;
    }
}
