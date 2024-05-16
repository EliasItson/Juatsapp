package persistencia;

import java.util.List;
import modelo.Usuario;
import org.bson.types.ObjectId;


public interface IUsuarioDAO 
{
    public List<Usuario> getAllUsuarios() throws PersistenciaException;
    public Boolean createUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario validateCredentials(String correo, String password) throws PersistenciaException;
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws PersistenciaException;
    public Usuario getUsuarioByCodigo(String codigo) throws PersistenciaException;
    public Usuario uploadPP(byte[] imageData, ObjectId usuario) throws PersistenciaException;
    public Usuario getUsuarioByCorreo(String correo) throws PersistenciaException;
}
