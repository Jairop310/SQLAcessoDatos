package dao;

import modelo.Videojuego;

public interface VideojuegoDAO {

	public boolean insertar(Videojuego videojuego);
    public boolean eliminar(Videojuego videojuego); 
    public boolean modificar(Videojuego videojuego);
    public Videojuego consultar(Videojuego videojuego);   
    public void finalize();

}
