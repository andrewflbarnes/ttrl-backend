package com.aflb.ttrl.server;

import com.aflb.ttrl.server.util.TtrlReporter;
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

        private static final String TEMPLATE = "******** %-20s: %s";

        public Reporter(BuildProperties buildProps, TtrlReporter ttrlReporter) {
            final String version = buildProps.getVersion();
            final String commit = buildProps.getCommit();
            final String versionLog = String.format(TEMPLATE,
                    "VERSION",
                    String.format("%s (%s)", version, commit));
            if (BuildProperties.UNKNOWN.equals(version) || BuildProperties.UNKNOWN.equals(commit)) {
                log.warn(versionLog);
            } else {
                log.info(versionLog);
            }

            ttrlReporter.report(TEMPLATE);
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
