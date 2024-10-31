package controlador;

import java.sql.Connection;

import dao.VideojuegoDAO;
import impl.VideojuegoImpl;
import modelo.Videojuego;

public class Principal {

	private static VideojuegoDAO videojuegoDAO;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		videojuegoDAO = new VideojuegoImpl();
		Videojuego newVideo = videojuegoDAO.consultar("Valorant");
		System.out.println(newVideo.toString());
	}
	

}
