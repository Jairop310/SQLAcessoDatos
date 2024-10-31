package conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conexion = null;
    private static String DRIVER;
    private static String URLDB;
    private static String USUARIO;
    private static String CLAVE;

    private static void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("db.properties")) {
            properties.load(input);
            DRIVER = properties.getProperty("DRIVER");
            URLDB = properties.getProperty("URLDB");
            USUARIO = properties.getProperty("USUARIO");
            CLAVE = properties.getProperty("CLAVE");
            Class.forName(DRIVER);
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo db.properties: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver JDBC: " + ex.getMessage());
        }
    }

    public static void createConnection() {
        loadProperties();
        try {
            conexion = DriverManager.getConnection(URLDB, USUARIO, CLAVE);
            if (conexion != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException ex) {
            System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
            System.out.printf("Mensaje   : %s %n", ex.getMessage());
            System.out.printf("SQL estado: %s %n", ex.getSQLState());
            System.out.printf("Cód error : %s %n", ex.getErrorCode());
        }
    }

    public static Connection getConnection() {
        return conexion;
    }

    public static void deleteConnection() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
