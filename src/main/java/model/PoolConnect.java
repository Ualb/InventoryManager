package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PoolConnect {

    private static Connection connection;

    public static Connection getConnection() {
        if(connection != null) {
            try {
                Runtime.getRuntime().addShutdownHook(new CloseConnection());
                connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static class CloseConnection extends Thread {
        // justo antes de cerrar el programa
        // la jvm ejecutara este codigo
        public void run () {
            try {
                Connection connection = getConnection();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
