package com.aflb.ttrl.server.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ttrl.api.secret")
@Component
@Data
public class ApiSecret {
    private boolean enable = false;
    private String token = "";

    public boolean validate(String check) {
        return !enable || token.equals(check);
    }

    public void validateThrow(String check) {
        if (enable && !token.equals(check)) {
            throw new InvalidApiSecretException();
        }
    }
}
