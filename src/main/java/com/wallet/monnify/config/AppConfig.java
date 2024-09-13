package com.wallet.monnify.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Getter
public class AppConfig {
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
    @Value("${monnify-get-reserved-account_transaction_url}")
    private String monnifyGetReservedAccountTransactionUrl;
    @Value("${monnify-contract-code}")
    private String monnifyContractCode;
    @Value("${account-ref-prefix}")
    private String accountReferencePrefix;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return mapper;
    }


}