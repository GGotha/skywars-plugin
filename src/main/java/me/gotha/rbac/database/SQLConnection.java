package me.gotha.rbac.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() {
        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");


            setConnection(connection);

            System.out.println("Conexão SQLite realizada com sucesso!");


        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

}