package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.api.UsersApi;
import com.aflb.ttrl.api.server.model.UserItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// TODO Proper CORS config (see corona plugin from penny-guess-server)
@CrossOrigin("*")
public class UsersController implements UsersApi {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserItem>> usersGet() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
