UPDATE t_ttrl_users
   SET wins = wins + 1
 WHERE discord_id = :discordId
