package com.techtricks.ecom_order_servise.config;

import java.time.Duration;
import java.util.UUID;

import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties.Retry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;

@Configuration
public class InventoryFeginClient {

    @Bean
    public Logger.Level feginInvLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(Duration.ofMillis(3000), Duration.ofMillis(5000), true);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1L, 2L, 3);

    }

    /*
     * Suppose an order fails because the inventory service returns an error.
     * Without a correlation ID, finding related logs across services can be
     * difficult.
     * With a correlation ID, you can search your logs:
     */

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("x-Correction-Id", UUID.randomUUID().toString());

        };
    }

}
