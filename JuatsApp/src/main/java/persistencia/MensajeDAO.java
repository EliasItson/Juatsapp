package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import modelo.Mensaje;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase que maneja las operaciones de persistencia relacionadas con los mensajes.
 */
public class MensajeDAO implements IMensajeDAO {

    private final MongoCollection<Mensaje> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();

    /**
     * Constructor de la clase MensajeDAO.
     */
    public MensajeDAO() {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("mensajes", Mensaje.class);
    }

    /**
     * Obtiene todos los mensajes.
     *
     * @return Lista de mensajes.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Mensaje> getAllMensajes() throws PersistenciaException {
        List<Mensaje> mensajes = new ArrayList<>();
        COLECCION.find().into(mensajes);

        return mensajes;
    }

    /**
     * Obtiene los mensajes enviados por un emisor.
     *
     * @param emisor ID del emisor.
     * @return Lista de mensajes enviados por el emisor.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws PersistenciaException {
        List<Mensaje> mensajes = new ArrayList<>();
        Bson filter = Filters.eq("emisor_id", emisor);
        COLECCION.find(filter).into(mensajes);

        return mensajes;
    }
    
    /**
     * Guarda un nuevo mensaje en la base de datos.
     *
     * @param mensaje El mensaje a guardar.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public void guardar(Mensaje mensaje) throws PersistenciaException {
        COLECCION.insertOne(mensaje);
    }
}
