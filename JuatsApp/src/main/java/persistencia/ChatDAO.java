package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import modelo.Chat;
import modelo.Mensaje;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class ChatDAO implements IChatDAO
{
    private final MongoCollection<Chat> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public ChatDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("chats", Chat.class);
    }
    
    @Override
    public List<Chat> getAllChats() throws PersistenciaException
    {
        List<Chat> chats = new ArrayList<>();
        COLECCION.find().into(chats);
        
        return chats;
    }
    
    
    @Override
    public void createChat(Chat chat) throws PersistenciaException
    {
        COLECCION.insertOne(chat);
    }
    
    public List<Chat> getChatsByUsuario(ObjectId usuario) throws PersistenciaException
    {
        List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.elemMatch("miembros", Filters.eq("_id", usuario))));
            pipeline.add(Aggregates.project(Projections.fields(
                    Projections.include("miembros"),
                    Projections.include("mensajes"),
                    Projections.include("isDeleted")
            )));

        List<Chat> chats = new ArrayList<>();
        COLECCION.aggregate(pipeline).into(chats);
        
        return chats;
    }
    
    public Chat getChatById(ObjectId chat) throws PersistenciaException
    {
        Bson filter = Filters.eq("_id", chat);
        return COLECCION.find(filter).first();
    }
    
    public void updateChat(Chat chat, Mensaje mensaje) throws PersistenciaException
    {
        Bson filter = Filters.eq("_id", chat.getId());
        Bson updateOperation = Updates.addToSet("mensajes", mensaje);
        COLECCION.updateOne(filter, updateOperation);
    }
}
