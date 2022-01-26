package me.gotha.rbac.commands;

import me.gotha.rbac.utils.MinecraftPosition;
import me.gotha.rbac.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeaveCommand implements CommandExecutor {

    private Connection connection;
    public final String commandName = Util.capitalize("leave");


    public LeaveCommand(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try {
            Statement statement = this.connection.createStatement();
            Player player = (Player) commandSender;
            String playerName = player.getName();


            String updatePlayerActiveFalseQuery = String.format("UPDATE lby_skywars SET active = false WHERE user_name = '%s';", playerName);


            statement.executeUpdate(updatePlayerActiveFalseQuery);


            Bukkit.broadcastMessage(ChatColor.GREEN + "Você saiu do lobby!");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
