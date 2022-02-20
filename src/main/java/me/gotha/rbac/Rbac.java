package me.gotha.rbac;

import me.gotha.rbac.commands.CreateCommand;
import me.gotha.rbac.commands.FeastCommand;
import me.gotha.rbac.commands.LeaveCommand;
import me.gotha.rbac.commands.LobbyCommand;
import me.gotha.rbac.database.Queries;
import me.gotha.rbac.database.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.bukkit.Bukkit.getServer;


public final class Rbac extends JavaPlugin {

    private Statement statement;

    public void createConnection() throws SQLException {
        System.out.println("Startando connection");
        SQLConnection sqlConnection = new SQLConnection();
        sqlConnection.connect();
        Connection connection = sqlConnection.getConnection();

        this.statement = connection.createStatement();
    }


    @Override
    public void onEnable() {
        try {
            this.createConnection();


            System.out.println("Turn on...");

            LobbyCommand lobby = new LobbyCommand(this.statement);
            FeastCommand feast = new FeastCommand();
            LeaveCommand leave = new LeaveCommand(this.statement);
            CreateCommand create = new CreateCommand();


            this.getCommand(lobby.commandName).setExecutor(lobby);
            this.getCommand(feast.commandName).setExecutor(feast);
            this.getCommand(leave.commandName).setExecutor(leave);
            this.getCommand(create.commandName).setExecutor(create);

            getServer().getPluginManager().registerEvents(lobby, this);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Turn off...");
        try {
            String disableLobby = "UPDATE lobbies SET active = false WHERE active=true;";
            String disablePlayersOnLobby = "UPDATE lobby_players SET active = false WHERE active=true;";

            this.statement.executeUpdate(disableLobby);
            this.statement.executeUpdate(disablePlayersOnLobby);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
