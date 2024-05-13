package negocio;

import java.time.LocalDate;
import java.util.List;
import modelo.Usuario;
import org.bson.types.ObjectId;


public interface IUsuarioNegocio 
{
    public List<Usuario> getAllUsuarios() throws NegocioException;
    public void createUsuario(String nombre, String correo, String password, String telefono, LocalDate fechaNacimiento, String sexo) throws NegocioException;
    public ObjectId validateCredentials(String correo, String password) throws NegocioException;
}
