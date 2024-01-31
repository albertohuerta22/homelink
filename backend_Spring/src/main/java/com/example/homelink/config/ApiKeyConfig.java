package com.example.homelink.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {

    @Value("${thirdparty.api.key}")
    private String thirdPartyApiKey;

    public String getThirdPartyApiKey() {
        return thirdPartyApiKey;
    }
}
