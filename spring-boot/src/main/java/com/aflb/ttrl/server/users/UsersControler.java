package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.api.UsersApi;
import com.aflb.ttrl.api.server.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersControler implements UsersApi {

    private final UserService userService;

    @Autowired
    public UsersControler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserItem>> usersGet() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
