package com.quipux.quipuxapi.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
    public DataSource dataSource() {
        // Configure o DataSource com o nome desejado
        return DataSourceBuilder.create()
        		.url("jdbc:h2:mem:root")
                .username("sa")
                .password("root")
                .build();
    }
}