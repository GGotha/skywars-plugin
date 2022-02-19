package me.gotha.rbac.commands;

import me.gotha.rbac.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeaveCommand implements CommandExecutor {

    private Statement statement;
    public final String commandName = Util.capitalize("leave");


    public LeaveCommand(Statement statement) {
        this.statement = statement;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try {
            Player player = (Player) commandSender;
            String playerName = player.getName();

            String getPlayer = String.format("SELECT * FROM players WHERE name = '%s';", playerName);
            ResultSet getPlayerResultSet = this.statement.executeQuery(getPlayer);

            int idPlayer;

            if (getPlayerResultSet.next()) {
                idPlayer = getPlayerResultSet.getInt("id");

                String updateActivePlayer = String.format("UPDATE lobby_players SET active = false WHERE id_player = %s and active = true;", idPlayer);
                this.statement.executeUpdate(updateActivePlayer);

                player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

                Bukkit.broadcastMessage(ChatColor.GREEN + "Você saiu do lobby!");
            }else {

                Bukkit.broadcastMessage(ChatColor.GREEN + "Você não está em nenhum lobby!");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
