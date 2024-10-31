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


	public boolean insertar(Videojuego videojuegoNuevo) {
		 boolean valor = false;
	        String sql = "INSERT INTO VIDEOJUEGOS (NAME,YEAR,GENERO) VALUES(?, ?, ?)";
	        PreparedStatement sentencia;
	        try {
	            sentencia = conexion.prepareStatement(sql);
	            sentencia.setString(1, videojuegoNuevo.getName());
	            sentencia.setInt(2, videojuegoNuevo.getYear());
	            sentencia.setString(3, videojuegoNuevo.getGenero());
	            int filas = sentencia.executeUpdate();
	            if (filas > 0) {
	                valor = true;
	                 System.out.printf("Videojuego " + videojuegoNuevo.getName() + " insertado%n");
	            }
	            sentencia.close();

	        } catch (SQLException e) {
	            mensajeExcepcion(e);      
	        }
	        return valor;
		
	}
	
    public boolean eliminar(Videojuego videojuegoEiminar) {
    	
    	boolean valor = false;
        String sql = "DELETE FROM VIDEOJUEGOS WHERE NAME = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, videojuegoEiminar.getName());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                valor = true;
            }
            sentencia.close();
        } catch (SQLException e) {
            mensajeExcepcion(e);      
        }
        return valor;
    }
    
    public boolean modificar(Videojuego dep) {
    	
    	boolean valor = false;
        String sql = "UPDATE VIDEOJUEGOS SET YEAR = ? , GENERO = ? WHERE NAME = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, dep.getYear());
            sentencia.setString(2, dep.getGenero());
            sentencia.setString(3, dep.getName());
            int filas = sentencia.executeUpdate();
            if (filas > 0) {
                valor = true;
                System.out.printf("Videojuego Modificado");
            }
            sentencia.close();
        } catch (SQLException e) {
            mensajeExcepcion(e);      
        }
        return valor;
    }
    
    public Videojuego consultar(Videojuego videojuegoConsultar) {
    	System.out.println(videojuegoConsultar.getName());
    	String sql = "SELECT name, year, genero FROM videojuegos WHERE name =  ?";
        PreparedStatement sentencia;
        Videojuego VideogameDevuelto = new Videojuego();        
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, videojuegoConsultar.getName());
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
                VideogameDevuelto.setName(rs.getString("name"));
                VideogameDevuelto.setGenero(rs.getString("genero"));
                VideogameDevuelto.setYear(rs.getInt("year"));
            }
          
            rs.close();
            sentencia.close();
         
        } catch (SQLException e) {
            mensajeExcepcion(e);            
        }
        return VideogameDevuelto;
    	
    }
    public void finalize() {
    	DB.deleteConnection();
    };

    private void mensajeExcepcion(SQLException e) {
	       System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }
}
