package me.gotha.rbac.database;

public class Queries {
//    public static String getAmountPlayersOnSkywarsLobby = "SELECT COUNT(*) AS lby_amount_players FROM lobbies WHERE active=true;";
    public static String updatePlayerWithActiveTrue = "UPDATE players SET active = true WHERE user_name = '%s';";
    public static String createPlayer = "INSERT INTO players (name, active, created_at, updated_at) VALUES ('%s', true, '2021-05-25', '2021-05-25');";
    public static String createLobby = "INSERT INTO lobbies (id_minigame, active, created_at, updated_at) VALUES (1, true, '2021-05-25', '2021-05-25');";
    public static String insertPlayerIntoLobby = "INSERT INTO lobbies (id_minigame, active, created_at, updated_at) VALUES (1, true, '2021-05-25', '2021-05-25');";
    public static String getActiveLobbies = "SELECT * FROM lobbies WHERE active = true";
    public static String getPlayersOnLobby = "SELECT COUNT(*) AS players_on_lobby FROM lobbies l INNER JOIN lobby_players lp ON l.id = lp.id_lobby;";
    public static String getPlayerWithTheSameNameAndActiveFalse = "SELECT * FROM players WHERE name = '%s' AND active=false;";
    public static String getPlayerWithTheSameNameAndActiveTrue = "SELECT * FROM players WHERE name = '%s' AND active=true;";

//    public static String setPlayerActiveToFalse = "UPDATE lby_skywars SET active = false WHERE user_name = '%s';";
//    public static String setAllPlayersActiveToFalse = "UPDATE lby_skywars SET active = false;";
}


// Usuário digita /lobby
// Lobby é criado
// Player é criado
// Player entra no lobby criado