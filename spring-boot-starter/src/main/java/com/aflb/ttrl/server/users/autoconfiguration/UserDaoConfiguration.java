package com.aflb.ttrl.server.users.autoconfiguration;

import com.aflb.ttrl.server.users.UserDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public UserDao dummyUserDao() {
        return new DummyUserDao();
    }
}
