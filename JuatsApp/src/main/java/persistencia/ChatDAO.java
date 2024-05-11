package persistencia;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import modelo.Mensaje;


public class ChatDAO implements IChatDAO
{
    private final MongoCollection<Mensaje> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public ChatDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("chats", Mensaje.class);
    }
    
    @Override
    public List<Mensaje> consultar() throws PersistenciaException
    {
        List<Mensaje> chats = new ArrayList<>();
        COLECCION.find().into(chats);
        
        return chats;
    }
    
    
    @Override
    public void guardar(Mensaje chat) throws PersistenciaException
    {
        COLECCION.insertOne(chat);
    }
}
