package com.aflb.ttrl.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DaoConfig {

    @Bean("database")
    public String database(DaoProperties props) {
        return props.getDatabase();
    }
}
