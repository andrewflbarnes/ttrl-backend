CREATE TABLE t_ttrl_users
(
  name       VARCHAR(255) PRIMARY KEY  NOT NULL
  ,
  discord_id VARCHAR(255)              NOT NULL
  ,
  high       INTEGER                   NOT NULL
  ,
  wins       INTEGER                   NOT NULL
  ,
  losses     INTEGER                   NOT NULL
  ,
  picture    VARCHAR(1023)
);

CREATE VIEW ttrl_users AS
  SELECT name, discord_id, wins, losses, high, picture
  FROM t_ttrl_users;

INSERT INTO t_ttrl_users (name, discord_id, wins, losses, high, picture)
VALUES ('Aidan Faria',
        'whowantsaids#5013',
        1,
        3,
        117,
        'https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/540614_10200626715614877_2102794417_n.jpg?_nc_cat=107&ccb=2&_nc_sid=de6eea&_nc_ohc=mABC6B70eL8AX_VBIVm&_nc_ht=scontent-lhr8-1.xx&oh=d9ca0a98cb65e55253ebd501503373da&oe=5FDA3833');

INSERT INTO t_ttrl_users (name, discord_id, wins, losses, high, picture)
VALUES ('Andrew Barnes',
        'barnesly#3670',
        3,
        1,
        116,
        'https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/535630_10150763070332182_888848364_n.jpg?_nc_cat=105&ccb=2&_nc_sid=09cbfe&_nc_ohc=Qu8ZcYq-p58AX9mK5fo&_nc_ht=scontent-lht6-1.xx&oh=95c3abd9488c9ba4346a7c9973782e20&oe=5FDC5B58');

INSERT INTO t_ttrl_users (name, discord_id, wins, losses, high, picture)
VALUES ('Mike Hutchings',
        'noseykart#5316',
        0,
        1,
        53,
        'https://scontent-lhr8-1.xx.fbcdn.net/v/t31.0-8/15195893_10207113262010697_1598240197302767699_o.jpg?_nc_cat=109&ccb=2&_nc_sid=cdbe9c&_nc_ohc=hKg6I7fevskAX-6SLhJ&_nc_ht=scontent-lhr8-1.xx&oh=72acd7b7a88c70a1a81f02540d15bd78&oe=5FDBC16D');
