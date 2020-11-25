UPDATE t_ttrl_users
   SET losses = losses + 1
 WHERE discord_id = :discordId
