package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DB;
import dao.PersonajesDAO;
import modelo.Personajes;
import modelo.Videojuego;

public class PersonajesImpl implements PersonajesDAO {
	
private Connection conexion ;
	
	public PersonajesImpl() {
        this.conexion = DB.getConnection();
        if (conexion == null) {
            DB.createConnection();
            this.conexion = DB.getConnection();
        }
    }
	
	@Override
	public boolean insertar(Personajes p) {

		boolean valor = false;
        String sql = "INSERT INTO personajes (name, tipo, videojuego_id) VALUES (?, ?, ?)";

        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, p.getNombre());
            sentencia.setString(2, p.getTipo());
            sentencia.setInt(3, p.getJuego());
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
	public boolean eliminar(Personajes p) {

		boolean valor = false;
        String sql = "DELETE FROM personajes WHERE name = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, p.getNombre());
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
        String sql = "UPDATE personajes SET tipo= ?, videojuego_id = ? WHERE name = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(3, p.getNombre());
            sentencia.setString(1, p.getTipo());
            sentencia.setInt(2, p.getJuego());
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
	public Personajes consultar(Personajes p) {

		String sql = "SELECT name, tipo, videojuego_id FROM personajes WHERE name =  ?";
        PreparedStatement sentencia;
        Personajes personaje_devuelto = new Personajes();        
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, p.getNombre());
            ResultSet rs = sentencia.executeQuery();          
            if (rs.next()) {
            	personaje_devuelto.setNombre(rs.getString("name"));
            	personaje_devuelto.setTipo(rs.getString("tipo"));
            	personaje_devuelto.setJuego(rs.getInt("videojuego_id"));
            }
            
            rs.close();
            sentencia.close();
         
        } catch (SQLException e) {
            mensajeExcepcion(e);            
        }
        return personaje_devuelto;
		
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
