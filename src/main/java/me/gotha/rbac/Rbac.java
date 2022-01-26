package me.gotha.rbac;

import me.gotha.rbac.commands.FeastCommand;
import me.gotha.rbac.commands.LeaveCommand;
import me.gotha.rbac.commands.LobbyCommand;
import me.gotha.rbac.database.SQLConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.Date;


public final class Rbac extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("Startando connection");
        SQLConnection sqlConnection = new SQLConnection();
        sqlConnection.connect();
        Connection connection = sqlConnection.getConnection();


        LobbyCommand lobby = new LobbyCommand(connection);
        FeastCommand feast = new FeastCommand();
        LeaveCommand leave = new LeaveCommand(connection);

        System.out.println("Turn on...");
        this.getCommand(lobby.commandName).setExecutor(lobby);
        this.getCommand(feast.commandName).setExecutor(feast);
        this.getCommand(leave.commandName).setExecutor(leave);

        getServer().getPluginManager().registerEvents(lobby, this);
    }

    @Override
    public void onDisable() {
        System.out.println("Turn off...");
    }
}
