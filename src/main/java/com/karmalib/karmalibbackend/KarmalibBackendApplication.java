package com.karmalib.karmalibbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
@EnableCaching
public class KarmalibBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(KarmalibBackendApplication.class, args);
    }
}
