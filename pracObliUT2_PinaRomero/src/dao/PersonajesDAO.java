package dao;

import modelo.Personajes;

public interface PersonajesDAO {

	public boolean insertar(Personajes p);
    public boolean eliminar(Personajes p); 
    public boolean modificar(Personajes p);
    public Personajes consultar(Personajes p);   


}
