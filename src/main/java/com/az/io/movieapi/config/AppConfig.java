package com.az.io.movieapi.config;

import com.az.io.movieapi.cache.CustomCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomCacheManager cacheManager() {
        return CustomCacheManager.getInstance();
    }
}
