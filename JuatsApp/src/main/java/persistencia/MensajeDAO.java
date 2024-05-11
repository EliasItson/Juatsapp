package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import modelo.Mensaje;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MensajeDAO implements IMensajeDAO
{
    private final MongoCollection<Mensaje> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public MensajeDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("mensajes", Mensaje.class);
    }
    
    @Override
    public List<Mensaje> getAllMensajes() throws PersistenciaException
    {
        List<Mensaje> mensajes = new ArrayList<>();
        COLECCION.find().into(mensajes);
        
        return mensajes;
    }

    @Override
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws PersistenciaException
    {
        List<Mensaje> mensajes = new ArrayList<>();
        Bson filter = Filters.eq("emisor_id", emisor);
        COLECCION.find(filter).into(mensajes);
        
        return mensajes;
    }
    
    @Override
    public void guardar(Mensaje mensaje) throws PersistenciaException
    {
        COLECCION.insertOne(mensaje);
    }
}
