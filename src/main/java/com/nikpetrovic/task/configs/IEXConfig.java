package com.nikpetrovic.task.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IEXConfig {
    @Getter
    @Value("${platform.iex.auth.token}")
    private String token;

    @Getter
    @Value("${platform.iex.url.prod}")
    private String url;
}
