package negocio;

import java.util.List;
import modelo.Mensaje;
import org.bson.types.ObjectId;


public interface IMensajeNegocio 
{
    public List<Mensaje> getAllMensajes() throws NegocioException;
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws NegocioException;
    public Mensaje guardar(String mensaje, ObjectId emisorId) throws NegocioException;
    public Mensaje guardar(byte[] imagen, ObjectId emisorId) throws NegocioException;
}
