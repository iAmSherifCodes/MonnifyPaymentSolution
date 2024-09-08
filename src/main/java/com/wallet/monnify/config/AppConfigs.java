package com.wallet.monnify.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigs {
    @Value("${youverify_base_url}")
    private String kycBaseUrl;
    @Value("${youverify_api_key}")
    private String kycApiKey;
    @Value("${youverify_test_data}")
    private String kycTestData;
    @Value("${youverify_test_data2}")
    private String kycTestData2;
    @Value("${monnify-api-key}")
    private String monnifyApiKey;
    @Value("${monnify-base-url}")
    private String monnifyBaseUrl;
    @Value("${monnify-api-secret}")
    private String monnifyApiSecretKey;
    @Value("${monnify-create-account}")
    private String monnifyCreateAccountUrl;
    @Value("${monnify-api-token}")
    private String monnifySignInUrl;
    @Value("${monnify-get-reserved-url}")
    private String monnifyGetReservedAccountUrl;
    @Value("${monnify-contract-code}")
    private String monnifyContractCode;

    public String getAccountReferencePrefix() {
        return accountReferencePrefix;
    }

    @Value("${account-ref-prefix}")
    private String accountReferencePrefix;

    public String getMonnifyContractCode() {
        return monnifyContractCode;
    }

    public String getKycBaseUrl() {
        return kycBaseUrl;
    }

    public String getKycApiKey() {
        return kycApiKey;
    }

    public String getKycTestData() {
        return kycTestData;
    }

    public String getKycTestData2() {
        return kycTestData2;
    }

    public String getMonnifyApiKey() {
        return monnifyApiKey;
    }

    public String getMonnifyBaseUrl() {
        return monnifyBaseUrl;
    }

    public String getMonnifyApiSecretKey() {
        return monnifyApiSecretKey;
    }

    public String getMonnifyCreateAccountUrl() {
        return monnifyCreateAccountUrl;
    }

    public String getMonnifySignInUrl() {
        return monnifySignInUrl;
    }

    public String getMonnifyGetReservedAccountUrl() {
        return monnifyGetReservedAccountUrl;
    }

}