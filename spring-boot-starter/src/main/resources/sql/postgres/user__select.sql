SELECT name
     , discord_id
     , wins
     , losses
     , high
     , picture
  FROM ttrl_users
 WHERE discord_id = :discord_id
