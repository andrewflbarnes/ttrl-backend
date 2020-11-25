package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;

import java.util.List;

public interface UserDao {
    List<UserItem> getUsers();
}
