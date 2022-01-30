package me.gotha.rbac;

import me.gotha.rbac.commands.FeastCommand;
import me.gotha.rbac.commands.LeaveCommand;
import me.gotha.rbac.commands.LobbyCommand;
import me.gotha.rbac.database.SQLConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public final class Rbac extends JavaPlugin {


    @Override
    public void onEnable() {
        try {
            System.out.println("Startando connection");
            SQLConnection sqlConnection = new SQLConnection();
            sqlConnection.connect();
            Connection connection = sqlConnection.getConnection();

            Statement statement = connection.createStatement();


            LobbyCommand lobby = new LobbyCommand(statement);
            FeastCommand feast = new FeastCommand();
            LeaveCommand leave = new LeaveCommand(statement);

            System.out.println("Turn on...");
            this.getCommand(lobby.commandName).setExecutor(lobby);
            this.getCommand(feast.commandName).setExecutor(feast);
            this.getCommand(leave.commandName).setExecutor(leave);

            getServer().getPluginManager().registerEvents(lobby, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Turn off...");
    }
}
