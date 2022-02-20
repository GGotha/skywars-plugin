package me.gotha.rbac.minigames;

import me.gotha.rbac.utils.MinecraftPosition;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

abstract class Skywars {


    public void sendInitialMessages(Player player) {
        String playerName = player.getName();

        Bukkit.broadcastMessage(StringUtils.repeat(" \n", 100));
        Bukkit.broadcastMessage(ChatColor.GREEN + "O jogador " + playerName + " entrou no lobby de skywars!");


    }

    public void createScoreboard(Player player) {

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        final Scoreboard board = scoreboardManager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("test", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Skywars".toUpperCase());


        Score score = objective.getScore(" ");
        score.setScore(4);

        Score score2 = objective.getScore(ChatColor.WHITE + "Restantes: " + ChatColor.GREEN + "9");
        score2.setScore(3);

        Score score3 = objective.getScore("");
        score3.setScore(2);

        Score score4 = objective.getScore(ChatColor.WHITE + "Abates: " + ChatColor.GREEN + "2");
        score4.setScore(1);

        player.setScoreboard(board);
    }

    public void movePlayerToMap(InventoryClickEvent event, Statement statement, int idLobby, int idPlayer) throws SQLException {
        Player player = (Player) event.getWhoClicked();

        String getLobby = String.format("SELECT l.level_name FROM lobbies l INNER JOIN lobby_players lp ON l.id = lp.id_lobby WHERE l.id = %s and lp.active = true and l.active = true;", idLobby);
        ResultSet getLobbyResultSet = statement.executeQuery(getLobby);
        String level_name = "";

        if (getLobbyResultSet.next()) {
            level_name = getLobbyResultSet.getString("level_name");

            getServer().dispatchCommand(player, String.format("mv tp world_%s", level_name));
        }


        World world = player.getWorld();

        ArrayList<MinecraftPosition> minecraftPositions = new ArrayList<>();

        if (level_name == "sky") {
            minecraftPositions.add(new MinecraftPosition(26.70, 17.20, -9.30));
            minecraftPositions.add(new MinecraftPosition(-24.30, 17.20, 10.70));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, -9.70));
            minecraftPositions.add(new MinecraftPosition(7.30, 17.20, -22.30));
            minecraftPositions.add(new MinecraftPosition(40.30, 17.20, 9.645));
            minecraftPositions.add(new MinecraftPosition(26.30, 17.20, 28.70));
            minecraftPositions.add(new MinecraftPosition(8.70, 17.20, 42.30));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, 28.30));
        }

        if (level_name == "catacombs") {
            minecraftPositions.add(new MinecraftPosition(26.70, 17.20, -9.30));
            minecraftPositions.add(new MinecraftPosition(-24.30, 17.20, 10.70));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, -9.70));
            minecraftPositions.add(new MinecraftPosition(7.30, 17.20, -22.30));
            minecraftPositions.add(new MinecraftPosition(40.30, 17.20, 9.645));
            minecraftPositions.add(new MinecraftPosition(26.30, 17.20, 28.70));
            minecraftPositions.add(new MinecraftPosition(8.70, 17.20, 42.30));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, 28.30));
        }

        if (level_name == "hexagonal") {
            minecraftPositions.add(new MinecraftPosition(26.70, 17.20, -9.30));
            minecraftPositions.add(new MinecraftPosition(-24.30, 17.20, 10.70));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, -9.70));
            minecraftPositions.add(new MinecraftPosition(7.30, 17.20, -22.30));
            minecraftPositions.add(new MinecraftPosition(40.30, 17.20, 9.645));
            minecraftPositions.add(new MinecraftPosition(26.30, 17.20, 28.70));
            minecraftPositions.add(new MinecraftPosition(8.70, 17.20, 42.30));
            minecraftPositions.add(new MinecraftPosition(-11.30, 17.20, 28.30));
        }

        Random random = new Random();
        int randomIndex = random.nextInt(8);

        MinecraftPosition selectedPlayerPosition = minecraftPositions.get(randomIndex);

        double x = selectedPlayerPosition.getX();
        double y = selectedPlayerPosition.getY();
        double z = selectedPlayerPosition.getZ();

        Location location = new Location(world, x, y, z);

        player.teleport(location);


    }

//    public void movePlayerToInitialPosition(Player player) {
//
//
//    }


}
