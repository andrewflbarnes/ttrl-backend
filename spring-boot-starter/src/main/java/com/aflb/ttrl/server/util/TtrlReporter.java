package com.aflb.ttrl.server.util;

import com.aflb.ttrl.server.api.ApiSecret;
import com.aflb.ttrl.server.config.DaoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Slf4j
public class TtrlReporter {

    private DaoProperties daoProperties;
    private ApiSecret apiSecret;

    public TtrlReporter(DaoProperties daoProperties, ApiSecret apiSecret) {
        this.daoProperties = daoProperties;
        this.apiSecret = apiSecret;
    }

    public void report(String template) {
        final String type = daoProperties.getType();
        final String database = daoProperties.getDatabase();
        final String daoLog = String.format(template,
                "DAO",
                String.format("%s (%s)", type, database));
        if (DaoProperties.UNSET.equals(type)) {
            log.warn(daoLog);
        } else {
            log.info(daoLog);
        }

        if (apiSecret.isEnable()) {
            log.info(String.format(template, "SECURE API", "enabled"));
        } else {
            log.warn(String.format(template, "SECURE API", "disabled"));
        }
    }
}
