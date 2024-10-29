package modelo;

import java.util.ArrayList;

public class Videojuego {

	String name;
	int year;
	String genero;
	ArrayList<Personajes> listP;
		
	
	public Videojuego(String nomber, int year, String genero) {
		super();
		this.name = nomber;
		this.year = year;
		this.genero = genero;
		this.listP = new ArrayList<Personajes>();	
	}
	
	
	public Videojuego() {
		super();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public ArrayList<Personajes> getListP() {
		return listP;
	}


	public void setListP(ArrayList<Personajes> listP) {
		this.listP = listP;
	}

}
