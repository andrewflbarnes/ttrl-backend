package com.aflb.ttrl.server.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ttrl.api.secret")
@Component
public class ApiSecret {

    @Getter
    @Setter
    private boolean enable = false;
    @Setter
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
