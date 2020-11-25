package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.api.server.model.UserOperation;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserItem> getUsers() {
        return userDao.getUsers();
    }

    public UserItem getUser(String discordId) {
        return userDao.getUser(discordId);
    }

    public void updateUser(String discordId, UserOperation op) {
        switch (op.getOperation()) {
            case WIN:
                userDao.incrementWins(discordId);
                break;
            case HIGH:
                userDao.updateHighScore(discordId, Integer.parseInt(op.getOpval()));
                break;
            case LOSE:
                userDao.incrementLosses(discordId);
                break;
            case PICTURE:
                String url = null;
                try {
                    url = URLDecoder.decode(op.getOpval(), StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException("Invalid encoded URL could not be decoded: " + op.getOpval());
                }
                userDao.updatePicture(discordId, url);
                break;
        }
    }
}
