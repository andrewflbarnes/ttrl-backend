package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.api.UsersApi;
import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.api.server.model.UserOperation;
import com.aflb.ttrl.api.server.model.UserUpdate;
import com.aflb.ttrl.server.api.ApiSecret;
import com.aflb.ttrl.server.users.exception.UserAlreadyExistsException;
import com.aflb.ttrl.server.users.exception.UserNonExistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        UserItem user = userService.getUser(discordId)
                .orElseThrow(() -> UserNonExistentException.ofDiscordId(discordId));

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> addUser(UserUpdate userUpdate) {
        secret.validateThrow(userUpdate.getSecret());

        String discordId = userUpdate.getDiscordId();

        if (!userService.addUser(asUserItem(userUpdate))) {
            throw UserAlreadyExistsException.ofDiscordId(discordId);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", URLEncoder.encode(discordId, StandardCharsets.UTF_8.name()))
                    .build();
        } catch (UnsupportedEncodingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to URL encode: " + discordId);
        }
    }

    @Override
    public ResponseEntity<Void> updateUser(String discordId, UserOperation op) {
        secret.validateThrow(op.getSecret());

        switch (op.getOperation()) {
            case WIN:
                userService.incrementWins(discordId);
                break;
            case HIGH:
                String opval = op.getOpval();
                try {
                    userService.updateHighScore(discordId, Integer.parseInt(opval));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("High score update must be numeric: " + opval);
                }
                break;
            case LOSE:
                userService.incrementLosses(discordId);
                break;
            case PICTURE:
                userService.updatePicture(discordId, op.getOpval());
                break;
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateUserFull(String discordId, UserUpdate userUpdate) {
        secret.validateThrow(userUpdate.getSecret());

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public UserItem asUserItem(UserUpdate user) {
        return new UserItem()
                .name(user.getName())
                .discordId(user.getDiscordId())
                .wins(user.getWins())
                .losses(user.getLosses())
                .high(user.getHigh())
                .picture(user.getPicture());
    }
}
