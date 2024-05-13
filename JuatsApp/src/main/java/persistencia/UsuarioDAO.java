package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class UsuarioDAO implements IUsuarioDAO
{
    private final MongoCollection<Usuario> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public UsuarioDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("usuarios", Usuario.class);
    }
    
    @Override
    public List<Usuario> getAllUsuarios() throws PersistenciaException
    {
        List<Usuario> usuarios = new ArrayList<>();
        COLECCION.find().into(usuarios);
        
        return usuarios;
    }
    
    @Override
    public void createUsuario(Usuario usuario) throws PersistenciaException
    {
        COLECCION.insertOne(usuario);
    }
    
    @Override
    public Usuario validateCredentials(String correo, String password) throws PersistenciaException
    {
        try
        {
            Bson filter = and(Filters.eq("correo", correo), Filters.eq("password", password));
            return COLECCION.find(filter).first();
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }
    
    @Override
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws PersistenciaException
    {
        try
        {
            Bson filter = and(Filters.eq("_id", usuarioId));
            return COLECCION.find(filter).first();
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }
}
