package dao;

import modelo.Personajes;

public interface PersonajesDAO {

	public boolean insertar(Personajes dep);
    public boolean eliminar(Personajes dep); 
    public boolean modificar(Personajes dep);
    public Personajes consultar(Personajes dep);   
    public void finalize();


}
