package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserItem> getUsers() {
        return userDao.getUsers();
    }

    public Optional<UserItem> getUser(String discordId) {
        return userDao.getUser(discordId);
    }

    public void incrementWins(String discordId) {
        userDao.incrementWins(discordId);
    }

    public void incrementLosses(String discordId) {
        userDao.incrementLosses(discordId);
    }

    public void updateHighScore(String discordId, int score) {
        userDao.updateHighScore(discordId, score);
    }

    public void updatePicture(String discordId, String picture) {
        userDao.updatePicture(discordId, picture);
    }

    public boolean addUser(UserItem user) {
        return userDao.addUser(user);
    }
}
