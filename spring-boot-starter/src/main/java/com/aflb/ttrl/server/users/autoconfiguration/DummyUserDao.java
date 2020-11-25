package com.aflb.ttrl.server.users.autoconfiguration;

import com.aflb.ttrl.api.server.model.UserItem;
import com.aflb.ttrl.server.users.UserDao;

import java.util.Arrays;
import java.util.List;

public class DummyUserDao implements UserDao {

    private static final List<UserItem> USERS = Arrays.asList(
            new UserItem()
                    .name("Aidan Farie")
                    .discordId("whowantsnads")
                    .wins(0)
                    .losses(1)
                    .high(117)
                    .picture("https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/540614_10200626715614877_2102794417_n.jpg?_nc_cat=107&ccb=2&_nc_sid=de6eea&_nc_ohc=mABC6B70eL8AX_VBIVm&_nc_ht=scontent-lhr8-1.xx&oh=d9ca0a98cb65e55253ebd501503373da&oe=5FDA3833"),
            new UserItem()
                    .name("Andrew Barnes")
                    .discordId("barnesly")
                    .wins(3)
                    .losses(1)
                    .high(116)
                    .picture("https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/535630_10150763070332182_888848364_n.jpg?_nc_cat=105&ccb=2&_nc_sid=09cbfe&_nc_ohc=Qu8ZcYq-p58AX9mK5fo&_nc_ht=scontent-lht6-1.xx&oh=95c3abd9488c9ba4346a7c9973782e20&oe=5FDC5B58"),
            new UserItem()
                    .name("Michael Hutchings")
                    .discordId("noseykart")
                    .wins(0)
                    .losses(1)
                    .high(53)
                    .picture("https://scontent-lhr8-1.xx.fbcdn.net/v/t31.0-8/15195893_10207113262010697_1598240197302767699_o.jpg?_nc_cat=109&ccb=2&_nc_sid=cdbe9c&_nc_ohc=hKg6I7fevskAX-6SLhJ&_nc_ht=scontent-lhr8-1.xx&oh=72acd7b7a88c70a1a81f02540d15bd78&oe=5FDBC16D")
    );

    @Override
    public List<UserItem> getUsers() {
        return USERS;
    }

    @Override
    public UserItem getUser(String discordId) {
        return null;
    }

    @Override
    public boolean addUser(UserItem user) {
        return false;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return false;
    }

    @Override
    public void incrementWins(String discordId) {

    }

    @Override
    public void incrementLosses(String discordId) {

    }

    @Override
    public void updateHighScore(String discordId, int score) {

    }

    @Override
    public void updatePicture(String discordId, String picture) {

    }
}
