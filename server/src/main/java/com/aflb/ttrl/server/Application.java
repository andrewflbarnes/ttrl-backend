package com.aflb.ttrl.server;

import com.aflb.ttrl.server.api.ApiSecret;
import com.aflb.ttrl.server.config.DaoProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@DependsOn("reporter")
public class Application {

    public static void main(String[] args) {
        configure(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder
                .sources(Application.class)
                .properties("spring.config.name:ttrl");
    }

    @Component("reporter")
    @Slf4j
    public static class Reporter {
        public Reporter(BuildProperties buildProps, DaoProperties daoProps, ApiSecret secret) {
            final String version = buildProps.getVersion();
            final String commit = buildProps.getCommit();
            if (BuildProperties.UNKNOWN.equals(version) || BuildProperties.UNKNOWN.equals(commit)) {
                log.warn("******** VERSION    : {} ({})", version, commit);
            } else {
                log.info("******** VERSION    : {} ({})", version, commit);
            }

            final String type = daoProps.getType();
            final String database = daoProps.getDatabase();
            if (DaoProperties.UNSET.equals(type)) {
                log.warn("******** DAO        : {} ({})", type, database);
            } else {
                log.info("******** DAO        : {} ({})", type, database);
            }

            if (secret.isEnable()) {
                log.info("******** SECURE API : enabled", type, database);
            } else {
                log.warn("******** SECURE API : disabled", type, database);
            }
        }
    }

    @Component
    @Data
    @PropertySource("classpath:build.yml")
    public static class BuildProperties {

        public static final String UNKNOWN = "UNKNOWN";

        @Value("${version}")
        private String version = UNKNOWN;

        @Value("${commit}")
        private String commit = UNKNOWN;
    }


}
