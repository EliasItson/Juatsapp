package persistencia;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import modelo.Chat;


public class ChatDAO implements IChatDAO
{
    private final MongoCollection<Chat> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public ChatDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("chats", Chat.class);
    }
    
    @Override
    public List<Chat> consultar() throws PersistenciaException
    {
        List<Chat> chats = new ArrayList<>();
        COLECCION.find().into(chats);
        
        return chats;
    }
    
    
    @Override
    public void guardar(Chat chat) throws PersistenciaException
    {
        COLECCION.insertOne(chat);
    }
}
