package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.api.UsersApi;
import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.api.server.model.UserOperation;
import com.aflb.ttrl.api.server.model.UserUpdate;
import com.aflb.ttrl.server.api.ApiSecret;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
// TODO Proper CORS config (see corona plugin from penny-guess-server)
@CrossOrigin("*")
public class UsersController implements UsersApi {

    private final UserService userService;
    private final ApiSecret secret;

    public UsersController(UserService userService, ApiSecret secret) {
        this.userService = userService;
        this.secret = secret;
    }

    @Override
    public ResponseEntity<List<UserItem>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<UserItem> getUser(String discordId) {
        UserItem user = userService.getUser(discordId);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> addUser(UserUpdate userUpdate) {
        if (!secret.validate(userUpdate.getSecret())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API secret");
        }

        return null;
    }

    @Override
    public ResponseEntity<Void> updateUser(String discordId, UserOperation userOperation) {
        if (!secret.validate(userOperation.getSecret())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API secret");
        }

        userService.updateUser(discordId, userOperation);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateUserFull(String discordId, UserUpdate userUpdate) {
        if (!secret.validate(userUpdate.getSecret())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API secret");
        }

        return null;
    }
}
