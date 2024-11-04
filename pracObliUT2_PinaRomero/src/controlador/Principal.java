package controlador;

import java.sql.Connection;
import java.util.Scanner;

import dao.PersonajesDAO;
import dao.VideojuegoDAO;
import impl.PersonajesImpl;
import impl.VideojuegoImpl;
import modelo.Personajes;
import modelo.Videojuego;

public class Principal {

	private static VideojuegoDAO videojuegoDAO;
	private static PersonajesDAO pdao;
	private static Scanner teclado;
	public static void main(String[] args) {
		
		
		char letra;
		pdao = new PersonajesImpl();
		videojuegoDAO = new VideojuegoImpl();
		teclado = new Scanner(System.in);
		do
		{
			System.out.println("¿En que tabla quieres hacer CRUD?(v o p), t para terminar");
			letra = teclado.nextLine().charAt(0);
			if (letra == 'v') {
				System.out.println("Introduzca una de las siguiente letra (i,d,u,s,t):");
				
				letra = teclado.nextLine().charAt(0);
				switch (letra)
				{
					case 'i': insertVideojuego(); break;
					case 'd': deleteVideojuego();break;
					case 'u': updateVideojuego(); break;
					case 's': selectVideojuego();break;
					case 't': break;
					default: System.out.println("No ha introcido la letra adecuada!");
				}
			}else if (letra == 'p') {
				System.out.println("Introduzca una de las siguiente letra (i,d,u,s,t):");
				
				letra = teclado.nextLine().charAt(0);
				switch (letra)
				{
					case 'i': insertPersonaje(); break;
					case 'd': deletePersonaje();break;
					case 'u': updatePersonaje(); break;
					case 's': selectPersonaje();break;
					case 't': break;
					default: System.out.println("No ha introcido la letra adecuada!");
				}
			}else if (teclado.nextLine().charAt(0) == 't'){
				break;
			}else {
				System.out.println("No ha introcido la letra adecuada!");
			}
				
			
		}
		while (letra != 't');
		pdao.finalize();
		videojuegoDAO.finalize();
		teclado.close();
	}
	private static void selectPersonaje() {
		
		System.out.println("Introduce el nombre del personaje");
		String nombre = teclado.nextLine();
		
		Personajes p = new Personajes();
		p.setNombre(nombre);
		Personajes pBuscado = pdao.consultar(p);
		
		if(pBuscado != null) {System.out.println(pBuscado.toString());}
		else {System.out.println("No se ha encontrado el personaje");}
	}
	private static void updatePersonaje() {
		System.out.println("Introduce el nombre del personaje");
		String nombre = teclado.nextLine();
		System.out.println("Introduce el nombre del videojuego");//Modificar para que pida el nombre
		String id = teclado.nextLine();
		System.out.println("Introduce el tipo del videojuego");
		String tipo = teclado.nextLine();
		
		Personajes p = new Personajes(nombre,tipo,id);
		if (pdao.modificar(p)) {System.out.println("Personaje modificado");}
		else {System.out.println("No se ha podido modificar el personaje");}
	}
	private static void deletePersonaje() {

		
		System.out.println("Introduce el nombre del personaje");
		String nombre = teclado.nextLine();
		Personajes p = new Personajes();
		p.setNombre(nombre);
		if (pdao.eliminar(p)) {System.out.println("Personaje eliminado");}
        else {System.out.println("No se ha podido eliminar el personaje");}
		
	}
	private static void insertPersonaje() {

		System.out.println("Introduce el nombre del personaje");
		String nombre = teclado.nextLine();
		System.out.println("Introduce el nombre del videojuego");//Modificar para que pida el nombre
		String id = teclado.nextLine();
		System.out.println("Introduce el tipo del videojuego");
		String tipo = teclado.nextLine();
		
		Personajes p = new Personajes(nombre,tipo,id);
		if (pdao.insertar(p)) {System.out.println("Personaje insertado");}
		else {System.out.println("No se ha podido insertar el personaje");}
		
	}
	private static void selectVideojuego() {
		System.out.println("Introduce el nombre del videojuego");
		String nombre = teclado.nextLine();
		
		Videojuego v = new Videojuego();
		v.setName(nombre);
		Videojuego vBuscado = videojuegoDAO.consultar(v);
		
		if(vBuscado != null) {System.out.println(vBuscado.toString());}
		else {System.out.println("No se ha encontrado el Videojuego");}
	}
	private static void updateVideojuego() {
		System.out.println("Introduce el nombre del videojuego");
		String nombre = teclado.nextLine();
		System.out.println("Introduce el año del videojuego");
		int year = Integer.parseInt(teclado.nextLine());
		System.out.println("Introduce el genero del videojuego");
		String genero = teclado.nextLine();
		
		Videojuego v = new Videojuego(nombre,year,genero);
		
		if (videojuegoDAO.modificar(v)) {System.out.println("Videojuego modificado");} 
		else {System.out.println("No se ha podido modificar el videojuego");}
		
	}
	private static void deleteVideojuego() {
		System.out.println("Introduce el nombre del videojuego");
		String nombre = teclado.nextLine();
		Videojuego v = new Videojuego();
		v.setName(nombre);
		
		if (videojuegoDAO.eliminar(v)) {System.out.println("Videojuego eliminado");} 
		else {System.out.println("No se ha podido eliminar el videojuego");}
		
	}
	private static void insertVideojuego() {
		System.out.println("Introduce el nombre del videojuego");
		String nombre = teclado.nextLine();
		System.out.println("Introduce el año del videojuego");
		int year = Integer.parseInt(teclado.nextLine());
		System.out.println("Introduce el genero del videojuego");
		String genero = teclado.nextLine();
		
		Videojuego v = new Videojuego(nombre,year,genero);
		
		if (videojuegoDAO.insertar(v)) {System.out.println("Videojuego insertado");} 
		else {System.out.println("No se ha podido insertar el videojuego");}
		
	}
	

}
