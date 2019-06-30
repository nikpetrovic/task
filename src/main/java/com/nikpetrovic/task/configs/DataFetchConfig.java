package com.nikpetrovic.task.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFetchConfig {
    @Value("${app.data-fetch.company-symbols}")
    private String[] companySymbols;

    public String[] getCompanySymbols() {
        return companySymbols;
    }
}
