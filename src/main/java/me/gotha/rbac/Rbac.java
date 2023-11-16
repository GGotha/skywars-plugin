package me.gotha.rbac;

import lombok.Getter;
import me.gotha.rbac.commands.CreateCommand;
import me.gotha.rbac.commands.FeastCommand;
import me.gotha.rbac.commands.LeaveCommand;
import me.gotha.rbac.commands.LobbyCommand;
import me.gotha.rbac.database.SQLConnection;
import me.gotha.rbac.database.SessionFactoryManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public final class Rbac extends JavaPlugin {

    private Statement statement;

    @Getter
    private static SessionFactory sessionFactory;

    @Getter
    private static Session session;

    public void createConnection() throws SQLException {
        System.out.println("Startando connection");
        SQLConnection sqlConnection = new SQLConnection();
        sqlConnection.connect();
        Connection connection = sqlConnection.getConnection();

        this.statement = connection.createStatement();
    }


    @Override
    public void onEnable() {
        System.out.println("Startando connection");

        SessionFactoryManager sessionFactoryManager = new SessionFactoryManager();
        sessionFactory = sessionFactoryManager.getSessionFactory();
        session = sessionFactory.openSession();

        System.out.println("Conexão com o banco de dados realizada com sucesso!");


        System.out.println("Turn on...");

        LobbyCommand lobby = new LobbyCommand(session);
        FeastCommand feast = new FeastCommand();
        LeaveCommand leave = new LeaveCommand(this.statement);
        CreateCommand create = new CreateCommand();


        this.getCommand(lobby.commandName).setExecutor(lobby);
        this.getCommand(feast.commandName).setExecutor(feast);
        this.getCommand(leave.commandName).setExecutor(leave);
        this.getCommand(create.commandName).setExecutor(create);

        getServer().getPluginManager().registerEvents(lobby, this);

    }

    @Override
    public void onDisable() {
        System.out.println("Turn off...");
//        try {
//            String disableLobby = "UPDATE lobbies SET active = false WHERE active=true;";
//            String disablePlayersOnLobby = "UPDATE lobby_players SET active = false WHERE active=true;";
//
//            this.statement.executeUpdate(disableLobby);
//            this.statement.executeUpdate(disablePlayersOnLobby);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
