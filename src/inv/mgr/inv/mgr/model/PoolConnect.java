package inv.mgr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase representa el punto de imperoterabilidad
 * entre JVM y SqLite
 */
public class PoolConnect {

    private static Connection connection;

    /**
     * Conexion a la base de datos SqLite
     * nombrada database.db
     *
     * @return clase de <code>java.sql.Connection</code>
     */
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
