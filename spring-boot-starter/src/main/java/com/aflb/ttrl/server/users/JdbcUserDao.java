package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.server.ResourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "ttrl.dao.type", havingValue = "jdbc")
public class JdbcUserDao implements UserDao {

    private final NamedParameterJdbcTemplate template;
    private final String sqlGetUsers;

    public JdbcUserDao(NamedParameterJdbcTemplate template, @Qualifier("database") String database) {
        this.template = template;
        this.sqlGetUsers = ResourceUtils.loadSqlResourceContents(database, "user__get.sql");
    }

    @Override
    public List<UserItem> getUsers() {
        return template.query(sqlGetUsers, (rs, i) -> new UserItem()
                .name(rs.getString("name"))
                .high(rs.getInt("high"))
                .losses(rs.getInt("losses"))
                .wins(rs.getInt("wins"))
                .picture(rs.getString("picture")));
    }
}
