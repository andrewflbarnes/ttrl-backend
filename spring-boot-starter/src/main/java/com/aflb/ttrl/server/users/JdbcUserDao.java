package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.server.ResourceUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "ttrl.dao.type", havingValue = "jdbc")
public class JdbcUserDao implements UserDao {

    private static final RowMapper<UserItem> ROW_MAPPER = new BeanPropertyRowMapper<>(UserItem.class);

    private final NamedParameterJdbcTemplate template;
    private final String sqlGetUsers;
    private final String sqlGetUser;
    private final String sqlUserExists;
    private final String sqlUserInsert;
    private final String sqlIncrementWins;
    private final String sqlIncrementLosses;
    private final String sqlUpdateHigh;
    private final String sqlUpdatePicture;
    private final String sqlUpdateName;

    public JdbcUserDao(NamedParameterJdbcTemplate template, @Qualifier("database") String database) {
        this.template = template;
        this.sqlGetUsers = ResourceUtils.loadSqlResourceContents(database, "user__select_all.sql");
        this.sqlGetUser = ResourceUtils.loadSqlResourceContents(database, "user__select.sql");
        this.sqlUserExists = ResourceUtils.loadSqlResourceContents(database, "user__exists.sql");
        this.sqlUserInsert = ResourceUtils.loadSqlResourceContents(database, "user__insert.sql");
        this.sqlIncrementLosses = ResourceUtils.loadSqlResourceContents(database, "user__update_increment_losses.sql");
        this.sqlIncrementWins = ResourceUtils.loadSqlResourceContents(database, "user__update_increment_wins.sql");
        this.sqlUpdateHigh = ResourceUtils.loadSqlResourceContents(database, "user__update_high.sql");
        this.sqlUpdatePicture = ResourceUtils.loadSqlResourceContents(database, "user__update_picture.sql");
        this.sqlUpdateName = ResourceUtils.loadSqlResourceContents(database, "user__update_name.sql");
    }

    @Override
    public List<UserItem> getUsers() {
        return template.query(sqlGetUsers, ROW_MAPPER);
    }

    @Override
    public Optional<UserItem> getUser(String discordId) {
        try {
            return Optional.ofNullable(template.queryForObject(
                    sqlGetUser, new MapSqlParameterSource("discordId", discordId), ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isUserExists(String discordId) {
        return template.queryForObject(sqlUserExists, discordIdParamSource(discordId), Integer.class) > 0;
    }

    @Override
    public boolean addUser(UserItem user) {
        if (isUserExists(user.getDiscordId())) {
            return false;
        }

        return template.update(sqlUserInsert, new BeanPropertySqlParameterSource(user)) == 1;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return false;
    }

    @Override
    public void incrementWins(String discordId) {
        template.update(sqlIncrementWins, discordIdParamSource(discordId));
    }

    @Override
    public void incrementLosses(String discordId) {
        template.update(sqlIncrementLosses, discordIdParamSource(discordId));
    }

    @Override
    public void updateHighScore(String discordId, int score) {
        template.update(
                sqlUpdateHigh,
                discordIdParamSource(discordId).addValue("high", score));
    }

    @Override
    public void updatePicture(String discordId, String picture) {
        template.update(
                sqlUpdatePicture,
                discordIdParamSource(discordId).addValue("picture", picture));
    }

    @Override
    public void updateName(String discordId, String name) {
        template.update(
                sqlUpdateName,
                discordIdParamSource(discordId).addValue("name", name));
    }

    private MapSqlParameterSource discordIdParamSource(String discordId) {
        return new MapSqlParameterSource("discordId", discordId);
    }
}
