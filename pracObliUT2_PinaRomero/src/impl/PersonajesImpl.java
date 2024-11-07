	package impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DB;
import dao.PersonajesDAO;
import modelo.Personajes;
import modelo.Videojuego;

public class PersonajesImpl implements PersonajesDAO {
	
	DB db;
	
	public PersonajesImpl() {
		db = DB.getInstancia();
    }
	
	@Override
	public boolean insertar(Personajes p) {

		boolean valor = false;
        String sql = "INSERT INTO personajes (name, tipo, videojuego_name,precio_unitario) VALUES (?, ?, ?,?)";
        String procedimientoSQL = "{call actualizar_valoracion_total (?) } "; 
        
        
        try(Connection conexion = db.getConnection();
        		PreparedStatement  sentencia = conexion.prepareStatement(sql);
        		CallableStatement llamada = conexion.prepareCall(procedimientoSQL);
        		) {
            sentencia.setString(1, p.getNombre());
            sentencia.setString(2, p.getTipo());
            sentencia.setString(3, p.getJuego());
            sentencia.setInt(4, p.getPrecio());
            int filas = sentencia.executeUpdate();
            
            llamada.setString(1, p.getNombre());
            llamada.executeUpdate();
            
            if (filas > 0) {
                valor = true;
            }
            sentencia.close();

        } catch (SQLException e) {
            mensajeExcepcion(e);      
        }
        return valor;
		
	}

	@Override
	public boolean eliminar(Personajes p) {

		boolean valor = false;
        String sql = "DELETE FROM personajes WHERE name = ? ";
        String procedimientoSQL = "{call actualizar_valoracion_total (?) } "; 
        
        try(Connection conexion = db.getConnection();
        		PreparedStatement  sentencia = conexion.prepareStatement(sql);
        		CallableStatement llamada = conexion.prepareCall(procedimientoSQL);) {
            sentencia.setString(1, p.getNombre());
            
            llamada.setString(1, p.getNombre());
            llamada.executeUpdate();
            
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

	@Override
	public boolean modificar(Personajes p) {

		boolean valor = false;
        String sql = "UPDATE personajes SET tipo= ?, videojuego_name = ?,precio_unitario = ? WHERE name = ? ";
        String procedimientoSQL = "{call actualizar_valoracion_total (?) } "; 
        DB basedatos = DB.getInstancia();
        try(Connection conexion = db.getConnection();
        		PreparedStatement  sentencia = conexion.prepareStatement(sql);
        		CallableStatement llamada = conexion.prepareCall(procedimientoSQL);
        		) {

            
            sentencia.setString(1, p.getTipo());
            sentencia.setString(2, p.getJuego());
            sentencia.setInt(3, p.getPrecio());
            sentencia.setString(4, p.getNombre());
            int filas = sentencia.executeUpdate();
            
            llamada.setString(1, p.getNombre());
            llamada.executeUpdate();
            if (filas > 0) {
                valor = true;
            }
            sentencia.close();
        } catch (SQLException e) {
           mensajeExcepcion(e);      
        }
        return valor;
		
	}

	@Override
	public Personajes consultar(Personajes p) {

		String sql = "SELECT * FROM personajes WHERE name =  ?";
        
        Personajes personaje_devuelto = new Personajes();      
        DB basedatos = DB.getInstancia();
        try(Connection conexion = db.getConnection();PreparedStatement  sentencia = conexion.prepareStatement(sql);) {
            sentencia.setString(1, p.getNombre());
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
            	personaje_devuelto.setNombre(rs.getString("name"));
            	personaje_devuelto.setTipo(rs.getString("tipo"));
            	personaje_devuelto.setJuego(rs.getString("videojuego_name"));
            	personaje_devuelto.setPrecio(rs.getInt("precio_unitario"));
            }
            
            rs.close();
            sentencia.close();
         
        } catch (SQLException e) {
            mensajeExcepcion(e);            
        }
        return personaje_devuelto;	
	}
	
    private void mensajeExcepcion(SQLException e) {
	       System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }

}
