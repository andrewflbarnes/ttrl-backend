package com.aflb.ttrl.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ttrl.dao")
@Data
@Component
public class DaoProperties {

    public static final String UNSET = "NONE";

    private String type = UNSET;
    private String database = "postgres";
}
