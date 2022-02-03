package me.gotha.rbac.commands;

import me.gotha.rbac.database.Queries;
import me.gotha.rbac.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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


            String updatePlayerActiveFalseQuery = String.format(Queries.setPlayerActiveToFalse, playerName);
            this.statement.executeUpdate(updatePlayerActiveFalseQuery);

            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

            Bukkit.broadcastMessage(ChatColor.GREEN + "Você saiu do lobby!");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
