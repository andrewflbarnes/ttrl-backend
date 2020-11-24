package com.aflb.ttrl.server.users;

import com.aflb.ttrl.api.server.model.UserItem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnMissingClass
public class DummyUserDao implements UserDao {

    private static final List<UserItem> USERS = Arrays.asList(
            new UserItem()
                    .name("Aidan Farie")
                    .wins(0)
                    .losses(1)
                    .high(117)
                    .picture("https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/540614_10200626715614877_2102794417_n.jpg?_nc_cat=107&ccb=2&_nc_sid=de6eea&_nc_ohc=mABC6B70eL8AX_VBIVm&_nc_ht=scontent-lhr8-1.xx&oh=d9ca0a98cb65e55253ebd501503373da&oe=5FDA3833"),
            new UserItem()
                    .name("Andrew Barnes")
                    .wins(3)
                    .losses(1)
                    .high(116)
                    .picture("https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/535630_10150763070332182_888848364_n.jpg?_nc_cat=105&ccb=2&_nc_sid=09cbfe&_nc_ohc=Qu8ZcYq-p58AX9mK5fo&_nc_ht=scontent-lht6-1.xx&oh=95c3abd9488c9ba4346a7c9973782e20&oe=5FDC5B58"),
            new UserItem()
                    .name("Michael Hutchings")
                    .wins(0)
                    .losses(1)
                    .high(53)
                    .picture("https://scontent-lhr8-1.xx.fbcdn.net/v/t31.0-8/15195893_10207113262010697_1598240197302767699_o.jpg?_nc_cat=109&ccb=2&_nc_sid=cdbe9c&_nc_ohc=hKg6I7fevskAX-6SLhJ&_nc_ht=scontent-lhr8-1.xx&oh=72acd7b7a88c70a1a81f02540d15bd78&oe=5FDBC16D")
    );

    @Override
    public List<UserItem> getUsers() {
        return USERS;
    }
}