package me.gotha.rbac.database;

public class Queries {
    public static String getAmountPlayersOnSkywarsLobby = "SELECT COUNT(*) AS lby_amount_players FROM lby_skywars WHERE active=true;";
    public static String updatePlayerWithActiveTrue = "UPDATE lby_skywars SET active = true WHERE user_name = '%s';";
    public static String createPlayer = "INSERT INTO lby_skywars (user_name, active, created_at, updated_at) VALUES ('%s', true, '2021-05-25', '2021-05-25');";
    public static String getPlayerWithTheSameNameAndActiveFalse = "SELECT * FROM lby_skywars WHERE user_name = '%s' AND active=false;";
    public static String getPlayerWithTheSameNameAndActiveTrue = "SELECT * FROM lby_skywars WHERE user_name = '%s' AND active=true;";
    public static String setPlayerActiveToFalse = "UPDATE lby_skywars SET active = false WHERE user_name = '%s';";
    public static String setAllPlayersActiveToFalse = "UPDATE lby_skywars SET active = false;";
}
