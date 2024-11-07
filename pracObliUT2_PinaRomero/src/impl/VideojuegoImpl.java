package impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import conexion.DB;
import dao.VideojuegoDAO;
import modelo.Videojuego;

public class VideojuegoImpl implements VideojuegoDAO {
	DB db;

	
	public VideojuegoImpl() {
        db = DB.getInstancia();
    }


	public boolean insertar(Videojuego videojuegoNuevo) {
		 	boolean valor = false;
	        String sql = "INSERT INTO VIDEOJUEGOS (NAME,YEAR,GENERO,PRECIO_UNITARIO,PRECIO_TOTAL) VALUES(?, ?, ?, ?, ?)";
	        
	        try(Connection conexion = db.getConnection();PreparedStatement  sentencia = conexion.prepareStatement(sql);) {
	        	
	            sentencia.setString(1, videojuegoNuevo.getName());
	            sentencia.setInt(2, videojuegoNuevo.getYear());
	            sentencia.setString(3, videojuegoNuevo.getGenero());
	            sentencia.setInt(4, videojuegoNuevo.getPrecioUnitario());
	            sentencia.setInt(5, videojuegoNuevo.getPrecioUnitario());
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
       
        
        try(Connection conexion = db.getConnection();PreparedStatement  sentencia = conexion.prepareStatement(sql);) {
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
        String sql = "UPDATE VIDEOJUEGOS SET YEAR = ? , GENERO = ?,precio_unitario = ?, precio_total = ? WHERE NAME = ? ";
        String procedimientoSQL = "{call actualizar_valoracion_total (?) } "; 
        DB basedatos = DB.getInstancia();
        
        try(Connection conexion = db.getConnection();
        		PreparedStatement  sentencia = conexion.prepareStatement(sql);
        		CallableStatement llamada = conexion.prepareCall(procedimientoSQL);) {
            sentencia.setInt(1, dep.getYear());
            sentencia.setString(2, dep.getGenero());
            sentencia.setString(5, dep.getName());
            sentencia.setInt(3, dep.getPrecioUnitario());
            sentencia.setInt(4, dep.getPrecioTotal());
            int filas = sentencia.executeUpdate();	
            
            
            llamada.setString(1, dep.getName());
            llamada.executeUpdate();

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
    	String sql = "SELECT * FROM videojuegos WHERE name =  ?";

        Videojuego VideogameDevuelto = new Videojuego();        
        
        try(Connection conexion = db.getConnection();PreparedStatement  sentencia = conexion.prepareStatement(sql);) {
            sentencia.setString(1, videojuegoConsultar.getName());
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
                VideogameDevuelto.setName(rs.getString("name"));
                VideogameDevuelto.setGenero(rs.getString("genero"));
                VideogameDevuelto.setYear(rs.getInt("year"));
                VideogameDevuelto.setPrecioUnitario(rs.getInt("precio_unitario"));
                VideogameDevuelto.setPrecioTotal(rs.getInt("precio_total"));
            }
          
            rs.close();
         
        } catch (SQLException e) {
            mensajeExcepcion(e);            
        }
        return VideogameDevuelto;
    	
    }

    private void mensajeExcepcion(SQLException e) {
	       System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }
}
