package com.wallet.monnify.user.dto.kyc;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class BvnRequestObject {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "isSubjectConsent")
    private boolean isSubjectConsent;

}
