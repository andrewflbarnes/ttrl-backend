CREATE ROLE ttrl_admin
  LOGIN
  CREATEROLE;

CREATE SCHEMA ttrl
  AUTHORIZATION ttrl_admin;

SET ROLE ttrl_admin;
SET SEARCH_PATH TO ttrl, public;

CREATE ROLE ttrl_rw;
GRANT USAGE ON SCHEMA ttrl TO ttrl_rw;

ALTER DEFAULT PRIVILEGES FOR ROLE ttrl_admin IN SCHEMA ttrl GRANT SELECT, INSERT, UPDATE, TRIGGER ON TABLES TO ttrl_rw;
ALTER DEFAULT PRIVILEGES FOR ROLE ttrl_admin IN SCHEMA ttrl GRANT ALL ON SEQUENCES TO ttrl_rw;
ALTER DEFAULT PRIVILEGES FOR ROLE ttrl_admin IN SCHEMA ttrl GRANT ALL ON FUNCTIONS TO ttrl_rw;

CREATE ROLE ttrl_user
  PASSWORD 'password'
  LOGIN
  INHERIT;
GRANT ttrl_rw TO ttrl_user;


CREATE TABLE t_ttrl_users
(   user_id    BIGSERIAL      NOT NULL
  , name       VARCHAR( 255 ) NOT NULL
  , discord_id VARCHAR( 255 ) NOT NULL
  , high       INTEGER        NOT NULL DEFAULT 0
  , wins       INTEGER        NOT NULL DEFAULT 0
  , losses     INTEGER        NOT NULL DEFAULT 0
  , picture    VARCHAR( 1023 )
  , PRIMARY KEY ( user_id )
);

CREATE UNIQUE INDEX users_idx_uq_user_id
  ON t_ttrl_users ( discord_id );

CREATE VIEW ttrl_users AS
  SELECT user_id, name, discord_id, wins, losses, high, picture
    FROM t_ttrl_users;


INSERT INTO t_ttrl_users ( name, discord_id, wins, losses, high, picture )
VALUES ( 'Test User'
       , 'test1#1111'
       , 1
       , 3
       , 117
       , 'https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/540614_10200626715614877_2102794417_n.jpg?_nc_cat=107&ccb=2&_nc_sid=de6eea&_nc_ohc=mABC6B70eL8AX_VBIVm&_nc_ht=scontent-lhr8-1.xx&oh=d9ca0a98cb65e55253ebd501503373da&oe=5FDA3833'
       );

INSERT INTO t_ttrl_users ( name, discord_id, wins, losses, high, picture )
VALUES ( 'Test User'
       , 'test2#2222'
       , 3
       , 1
       , 116
       , 'https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/535630_10150763070332182_888848364_n.jpg?_nc_cat=105&ccb=2&_nc_sid=09cbfe&_nc_ohc=Qu8ZcYq-p58AX9mK5fo&_nc_ht=scontent-lht6-1.xx&oh=95c3abd9488c9ba4346a7c9973782e20&oe=5FDC5B58'
       );

INSERT INTO t_ttrl_users ( name, discord_id, wins, losses, high, picture )
VALUES ( 'Test User'
       , 'test3#3333'
       , 0
       , 1
       , 53
       , 'https://scontent-lhr8-1.xx.fbcdn.net/v/t31.0-8/15195893_10207113262010697_1598240197302767699_o.jpg?_nc_cat=109&ccb=2&_nc_sid=cdbe9c&_nc_ohc=hKg6I7fevskAX-6SLhJ&_nc_ht=scontent-lhr8-1.xx&oh=72acd7b7a88c70a1a81f02540d15bd78&oe=5FDBC16D'
       );


DROP SCHEMA ttrl CASCADE;
DROP ROLE ttrl_user;
DROP ROLE ttrl_rw;
DROP ROLE ttrl_admin;
