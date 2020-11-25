package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.server.ResourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@ConditionalOnProperty(name = "ttrl.dao.type", havingValue = "jdbc")
public class JdbcUserDao implements UserDao {

    private static final RowMapper<UserItem> ROW_MAPPER = (rs, i) -> new UserItem()
            .name(rs.getString("name"))
            .discordId(rs.getString("discord_id"))
            .high(rs.getInt("high"))
            .losses(rs.getInt("losses"))
            .wins(rs.getInt("wins"))
            .picture(rs.getString("picture"));

    private final NamedParameterJdbcTemplate template;
    private final String sqlGetUsers;
    private final String sqlGetUser;
    private final String sqlIncrementWins;
    private final String sqlIncrementLosses;
    private final String sqlUpdateHigh;
    private final String sqlUpdatePicture;

    public JdbcUserDao(NamedParameterJdbcTemplate template, @Qualifier("database") String database) {
        this.template = template;
        this.sqlGetUsers = ResourceUtils.loadSqlResourceContents(database, "user__select_all.sql");
        this.sqlGetUser = ResourceUtils.loadSqlResourceContents(database, "user__select.sql");
        this.sqlIncrementLosses = ResourceUtils.loadSqlResourceContents(database, "user__update_increment_losses.sql");
        this.sqlIncrementWins = ResourceUtils.loadSqlResourceContents(database, "user__update_increment_wins.sql");
        this.sqlUpdateHigh = ResourceUtils.loadSqlResourceContents(database, "user__update_high.sql");
        this.sqlUpdatePicture = ResourceUtils.loadSqlResourceContents(database, "user__update_picture.sql");
    }

    @Override
    public List<UserItem> getUsers() {
        return template.query(sqlGetUsers, ROW_MAPPER);
    }

    @Override
    public UserItem getUser(String discordId) {
        return template.queryForObject(sqlGetUser, new MapSqlParameterSource("discord_id", discordId), ROW_MAPPER);
    }

    @Override
    public boolean addUser(UserItem user) {
        return false;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return false;
    }

    @Override
    public void incrementWins(String discordId) {
        template.update(sqlIncrementWins, new MapSqlParameterSource("discord_id", discordId));
    }

    @Override
    public void incrementLosses(String discordId) {
        template.update(sqlIncrementLosses, new MapSqlParameterSource("discord_id", discordId));
    }

    @Override
    public void updateHighScore(String discordId, int score) {
        template.update(sqlUpdateHigh, new MapSqlParameterSource()
                .addValue("discord_id", discordId)
                .addValue("high", score));
    }

    @Override
    public void updatePicture(String discordId, String picture) {
        template.update(sqlUpdatePicture, new MapSqlParameterSource()
                .addValue("discord_id", discordId)
                .addValue("picture", picture));
    }
}
