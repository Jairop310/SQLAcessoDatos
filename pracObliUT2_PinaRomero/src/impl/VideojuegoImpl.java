package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DB;
import dao.VideojuegoDAO;
import modelo.Videojuego;

public class VideojuegoImpl implements VideojuegoDAO {

	private Connection conexion ;
	
	public VideojuegoImpl() {
        this.conexion = DB.getConnection();
        if (conexion == null) {
            DB.createConnection();
            this.conexion = DB.getConnection();
        }
    }


	public boolean insertar(Videojuego dep) {
		
		return false;
		
	}
    public boolean eliminar(Videojuego dep) {
		return false;
    	
    }
    public boolean modificar(Videojuego dep) {
		return false;
    	
    }
    public Videojuego consultar(String name) {
    	
    	String sql = "SELECT name, year, genero FROM videojuegos WHERE name =  ?";
        PreparedStatement sentencia;
        Videojuego VideogameDevuelto = new Videojuego();        
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, name);
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
                VideogameDevuelto.setName(rs.getString("name"));
                VideogameDevuelto.setGenero(rs.getString("genero"));
                VideogameDevuelto.setYear(rs.getInt("year"));
            }
            else
                System.out.printf("Videojuego no existe");
            
            rs.close();
            sentencia.close();
         
        } catch (SQLException e) {
            mensajeExcepcion(e);            
        }
        return VideogameDevuelto;
    	
    }
    public void finalize() {
    	
    };

    private void mensajeExcepcion(SQLException e) {
	       System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }
}
