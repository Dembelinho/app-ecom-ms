package com.example.billingservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "token")
@Data
@Component
public class ConsulConfig {
    private long accessTokenTimeout;
    private long refreshTokenTimeout;
}
