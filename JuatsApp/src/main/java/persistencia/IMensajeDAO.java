package persistencia;

import java.util.List;
import modelo.Mensaje;
import org.bson.types.ObjectId;


public interface IMensajeDAO 
{
    List<Mensaje> getAllMensajes() throws PersistenciaException;
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws PersistenciaException;
    void guardar(Mensaje mensaje) throws PersistenciaException;
}
