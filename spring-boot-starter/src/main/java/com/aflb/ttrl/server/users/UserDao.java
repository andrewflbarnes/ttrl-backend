package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;

import java.util.List;

public interface UserDao {
    List<UserItem> getUsers();

    UserItem getUser(String discordId);

    boolean addUser(UserItem user);

    boolean updateUser(UserItem user);

    void incrementWins(String discordId);

    void incrementLosses(String discordId);

    void updateHighScore(String discordId, int score);

    void updatePicture(String discordId, String picture);
}
