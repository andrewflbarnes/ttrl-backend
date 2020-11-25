package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import org.springframework.stereotype.Service;

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
}