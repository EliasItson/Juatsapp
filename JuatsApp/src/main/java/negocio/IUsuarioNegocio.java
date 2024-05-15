package negocio;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import modelo.Usuario;
import org.bson.types.ObjectId;


public interface IUsuarioNegocio 
{
    public List<Usuario> getAllUsuarios() throws NegocioException;
    public void createUsuario(String nombre, String correo, String password, String salt, String telefono, LocalDate fechaNacimiento, String sexo) throws NegocioException;
    public Usuario validateCredentials(String correo, String password) throws NegocioException;
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws NegocioException;
    public Usuario getUsuarioByCodigo(String codigo) throws NegocioException;
    public Usuario uploadPP(File imageFile, ObjectId usuario) throws NegocioException;
}
