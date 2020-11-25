package com.aflb.ttrl.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ttrl.dao")
@Data
@Component
public class DaoProperties {
    private String database = "postgres";

    @Bean("database")
    public String database() {
        return database;
    }
}
