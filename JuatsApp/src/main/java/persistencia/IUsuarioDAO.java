package persistencia;

import java.util.List;
import modelo.Usuario;


public interface IUsuarioDAO 
{
    public List<Usuario> getAllUsuarios() throws PersistenciaException;
    public void createUsuario(Usuario usuario) throws PersistenciaException;
    

}
