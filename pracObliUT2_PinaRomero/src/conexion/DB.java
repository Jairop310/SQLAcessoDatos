package conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DB {

    private static Connection conexion = null;
    private static MysqlDataSource dataSource;
    private static String DRIVER;
    private static String URLDB;
    private static String USUARIO;
    private static String CLAVE;
    
    private static DB baseDatos;
    
    
    public DB() {
		loadProperties();
	}

	public static DB getInstancia() {
		if (baseDatos == null) {
			baseDatos = new DB();
		}
		return baseDatos;
	}
	
	private void loadProperties() {
        Properties properties = desincriptacion();
        try (FileInputStream input = new FileInputStream("generado.properties")) {
            properties.load(input);
            DRIVER = properties.getProperty("jdbc.protocolo");
            URLDB = properties.getProperty("jdbc.basedatos");
            USUARIO = properties.getProperty("jdbc.username");
            CLAVE = properties.getProperty("jdbc.password");
            
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo db.properties: " + ex.getMessage());
        } 
    }
    private  Properties desincriptacion() {
    	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("PASSWORD");
		Properties props = new EncryptableProperties(encryptor);
		return props;
    }

    public  void createConnection() {
        //loadProperties();
        try {
            dataSource = new MysqlDataSource();
            dataSource.setURL(URLDB); 
            dataSource.setUser(USUARIO);
            dataSource.setPassword(CLAVE);
        	conexion = dataSource.getConnection();
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

    public  Connection getConnection() {
    	if (conexion == null) {
            createConnection();
    	}
        return conexion;
    }

    public  void deleteConnection() {
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
