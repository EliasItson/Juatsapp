package persistencia;

import java.util.List;
import modelo.Usuario;


public interface IUsuarioDAO 
{
    public List<Usuario> consultar() throws PersistenciaException;
    public void guardar(Usuario usuario) throws PersistenciaException;

}
