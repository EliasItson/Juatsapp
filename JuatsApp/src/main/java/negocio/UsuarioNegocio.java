package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
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
        Random random = new Random();
        int randomNumber = random.nextInt(16777216);
        String codigo = String.format("%06X", randomNumber);
        
        try 
        {
            Usuario usuario = new Usuario( nombre, correo, password,  telefono, fechaNacimiento, sexo, codigo);
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
    public Usuario validateCredentials(String correo, String password) throws NegocioException
    {
        try 
        {
            return usuarioDAO.validateCredentials(correo, password);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws NegocioException
    {
        try 
        {

            return usuarioDAO.getTrimmedUsuarioById(usuarioId);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
}
