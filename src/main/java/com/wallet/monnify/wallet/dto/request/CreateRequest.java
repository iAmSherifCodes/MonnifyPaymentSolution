package com.wallet.monnify.wallet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateRequest {
    @JsonProperty
    private String accountName; //
    @JsonProperty
    private String accountReference; //
    @JsonProperty
    private String currencyCode; //
    @JsonProperty
    private String contractCode; //
    @JsonProperty
    private String customerEmail;//
    @JsonProperty
    private String customerName;//
    @JsonProperty
    private String bvn;//
    @JsonProperty
    private String nin;//
    @JsonProperty
    private Boolean getAllAvailableBanks;
    private String token;
    @JsonProperty(value = "preferredBanks")
    private List<String> preferredBanks;
}
