package modelo;

public class Personajes {

	String nombre;
	String tipo;
	int juego;
	
	public Personajes(String nombre, String tipo, int juego) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.juego = juego;
	}

	public Personajes() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getJuego() {
		return juego;
	}

	public void setJuego(int juego) {
		this.juego = juego;
	}

	@Override
	public String toString() {
		return "Personajes [nombre=" + nombre + ", tipo=" + tipo + ", juego=" + juego + "]";
	}
	
	

}
