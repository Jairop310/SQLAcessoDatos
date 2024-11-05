package modelo;

import java.util.ArrayList;

public class Videojuego {

	String name;
	int year;
	String genero;
	ArrayList<Personajes> listP;
	int precioUnitario;
	int precioTotal;
		
	
	public Videojuego(String nomber, int year, String genero,int precioUnitario, int precioTotal) {
		super();
		this.name = nomber;
		this.year = year;
		this.genero = genero;
		this.precioUnitario = precioUnitario;
		this.precioTotal = precioTotal;
		this.listP = new ArrayList<Personajes>();	
	}
	
	


	@Override
	public String toString() {
		return "Videojuego [name=" + name + ", year=" + year + ", genero=" + genero + ", listP=" + listP
				+ ", precioUnitario=" + precioUnitario + ", precioTotal=" + precioTotal + "]";
	}




	public int getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public int getPrecioTotal() {
		return precioTotal;
	}


	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
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
