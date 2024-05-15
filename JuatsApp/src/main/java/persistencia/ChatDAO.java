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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase que maneja las operaciones de persistencia relacionadas con los chats.
 */
public class ChatDAO implements IChatDAO {

    private final MongoCollection<Chat> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();

    /**
     * Constructor de la clase ChatDAO.
     */
    public ChatDAO() {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("chats", Chat.class);
    }

    /**
     * Obtiene todos los chats.
     *
     * @return Lista de chats.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Chat> getAllChats() throws PersistenciaException {
        List<Chat> chats = new ArrayList<>();
        COLECCION.find().into(chats);

        return chats;
    }

    /**
     * Crea un nuevo chat.
     *
     * @param chat El chat a crear.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public void createChat(Chat chat) throws PersistenciaException {
        COLECCION.insertOne(chat);
    }

    /**
     * Obtiene los chats de un usuario.
     *
     * @param usuario ID del usuario.
     * @return Lista de chats del usuario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Chat> getChatsByUsuario(ObjectId usuario) throws PersistenciaException {
        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.match(Filters.elemMatch("miembros", Filters.eq("_id", usuario))));
        pipeline.add(Aggregates.match(Filters.eq("isDeleted", false)));
        pipeline.add(Aggregates.project(Projections.fields(
                Projections.include("miembros"),
                Projections.include("mensajes"),
                Projections.include("isDeleted")
        )));

        List<Chat> chats = new ArrayList<>();
        COLECCION.aggregate(pipeline).into(chats);

        return chats;
    }

    /**
     * Obtiene un chat por su ID.
     *
     * @param chat ID del chat.
     * @return El chat correspondiente al ID.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Chat getChatById(ObjectId chat) throws PersistenciaException {
        Bson filter = Filters.eq("_id", chat);
        return COLECCION.find(filter).first();
    }

    /**
     * Actualiza un chat con un nuevo mensaje.
     *
     * @param chat    El chat a actualizar.
     * @param mensaje El mensaje a agregar al chat.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public void updateChat(Chat chat, Mensaje mensaje) throws PersistenciaException {
        Bson filter = Filters.eq("_id", chat.getId());
        Bson updateOperation = Updates.addToSet("mensajes", mensaje);
        COLECCION.updateOne(filter, updateOperation);
    }

    /**
     * Marca un chat como eliminado.
     *
     * @param chat ID del chat.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public void deleteChat(ObjectId chat) throws PersistenciaException {
        Bson filter = Filters.eq("_id", chat);
        Bson updateOperation = Updates.set("isDeleted", true);
        COLECCION.updateOne(filter, updateOperation);
    }
}
