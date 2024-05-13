package negocio;

import java.time.LocalDate;
import java.util.List;
import modelo.Usuario;
import org.bson.types.ObjectId;
import persistencia.PersistenciaException;
import persistencia.UsuarioDAO;


public class UsuarioNegocio implements IUsuarioNegocio
{
    UsuarioDAO usuarioDAO;
    
    public UsuarioNegocio() 
    {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    public List<Usuario> getAllUsuarios() throws NegocioException
    {
        try 
        {
            List<Usuario> usuarios = this.usuarioDAO.getAllUsuarios();
            if (usuarios == null) 
            {
                throw new NegocioException("No existen usuarios registrados");
            }

            return usuarios;
        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public void createUsuario(String nombre, String correo, String password, String telefono, LocalDate fechaNacimiento, String sexo) throws NegocioException
    {
        try 
        {
            Usuario usuario = new Usuario( nombre, correo, password,  telefono, fechaNacimiento, sexo);
            if (usuario == null) 
            {
                throw new NegocioException("No se proporciono un usuario valido");
            }

            usuarioDAO.createUsuario(usuario);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public ObjectId validateCredentials(String correo, String password) throws NegocioException
    {
        try 
        {

            usuarioDAO.validateCredentials(correo, password);
            return usuarioDAO.validateCredentials(correo, password);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
}
