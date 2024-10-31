package dao;

import modelo.Videojuego;

public interface VideojuegoDAO {

	public boolean insertar(Videojuego dep);
    public boolean eliminar(Videojuego dep); 
    public boolean modificar(Videojuego dep);
    public Videojuego consultar(String name);   
    public void finalize();

}
