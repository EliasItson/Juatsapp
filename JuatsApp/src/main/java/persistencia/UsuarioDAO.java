package persistencia;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;


public class UsuarioDAO implements IUsuarioDAO
{
    private final MongoCollection<Usuario> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public UsuarioDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("usuarios", Usuario.class);
    }
    
    @Override
    public List<Usuario> consultar() throws PersistenciaException
    {
        List<Usuario> usuarios = new ArrayList<>();
        COLECCION.find().into(usuarios);
        
        return usuarios;
    }
    
    
    @Override
    public void guardar(Usuario usuario) throws PersistenciaException
    {
        COLECCION.insertOne(usuario);
    }
}
