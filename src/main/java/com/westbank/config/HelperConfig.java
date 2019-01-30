package com.westbank.config;

import com.westbank.AppProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.westbank.helper", "com.westbank.ws"})
@Import(AppProperties.class)
public class HelperConfig {
}
