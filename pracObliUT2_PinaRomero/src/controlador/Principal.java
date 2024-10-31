package controlador;

import java.sql.Connection;

import dao.PersonajesDAO;
import dao.VideojuegoDAO;
import impl.PersonajesImpl;
import impl.VideojuegoImpl;
import modelo.Personajes;
import modelo.Videojuego;

public class Principal {

//	private static VideojuegoDAO videojuegoDAO;
	private static PersonajesDAO pdao;
	public static void main(String[] args) {
		
		pdao = new PersonajesImpl();
		
		
		// TODO Auto-generated method stub
//		videojuegoDAO = new VideojuegoImpl();
//		Videojuego newVideo = videojuegoDAO.consultar("Valorant");
//		System.out.println(newVideo.toString());
		
//		Personajes new_personaje = new Personajes("Zed", "", 0);
//		Personajes nuevo_personaje = pdao.consultar(new_personaje);
//		System.out.println(nuevo_personaje.toString());
		
//		Personajes new_personaje = new Personajes("Cientifico", "Trabajador", 4);
//		pdao.insertar(new_personaje);
		
//		Personajes new_personaje = new Personajes("Cientifico", "", 0);
//		pdao.eliminar(new_personaje);
		
//		Personajes new_personaje = new Personajes("John Wick", "Cientifico", 1);
//	    boolean modificado = pdao.modificar(new_personaje);
		
		conexion.DB.deleteConnection();
	}
	

}
