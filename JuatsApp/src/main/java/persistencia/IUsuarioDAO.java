package persistencia;

import java.util.List;
import modelo.Usuario;
import org.bson.types.ObjectId;


public interface IUsuarioDAO 
{
    public List<Usuario> getAllUsuarios() throws PersistenciaException;
    public void createUsuario(Usuario usuario) throws PersistenciaException;
    public ObjectId validateCredentials(String correo, String password) throws PersistenciaException;

}
