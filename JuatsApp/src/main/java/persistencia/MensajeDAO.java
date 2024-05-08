package persistencia;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import modelo.Mensaje;

public class MensajeDAO implements IMensajeDAO
{
    private final MongoCollection<Mensaje> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();
    
    public MensajeDAO()
    {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("mensajes", Mensaje.class);
    }
    
    @Override
    public List<Mensaje> consultar() throws PersistenciaException
    {
        List<Mensaje> mensajes = new ArrayList<>();
        COLECCION.find().into(mensajes);
        
        return mensajes;
    }
    
    
    @Override
    public void guardar(Mensaje mensaje) throws PersistenciaException
    {
        COLECCION.insertOne(mensaje);
    }
}
