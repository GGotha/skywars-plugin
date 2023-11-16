package me.gotha.rbac.database;

import me.gotha.rbac.Rbac;
import me.gotha.rbac.entities.Lobby;
import me.gotha.rbac.entities.LobbyPlayer;
import me.gotha.rbac.entities.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {
    private SessionFactory sessionFactory;

    public SessionFactoryManager() {
        try {
           Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/skywars_development")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "root")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.current_session_context_class", "thread");

           configuration.addAnnotatedClass(Lobby.class);
           configuration.addAnnotatedClass(LobbyPlayer.class);
           configuration.addAnnotatedClass(Player.class);

           sessionFactory = configuration.buildSessionFactory();


        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public SessionFactory getSessionFactory() { return sessionFactory; }
}