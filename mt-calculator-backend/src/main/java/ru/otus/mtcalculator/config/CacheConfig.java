package ru.otus.mtcalculator.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    private static final int CACHE_ELEM_LIFE_TIME = 10;

    @Bean
    public CacheManager cacheManager() {

        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(@NonNull String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(CACHE_ELEM_LIFE_TIME, TimeUnit.SECONDS)
                                .build().asMap(),
                        false);
            }
        };
    }

}
