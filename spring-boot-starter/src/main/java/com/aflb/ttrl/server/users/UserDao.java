package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<UserItem> getUsers();

    Optional<UserItem> getUser(String discordId);

    boolean isUserExists(String discordId);

    boolean addUser(UserItem user);

    boolean updateUser(UserItem user);

    void incrementWins(String discordId);

    void incrementLosses(String discordId);

    void updateHighScore(String discordId, int score);

    void updatePicture(String discordId, String picture);
}
