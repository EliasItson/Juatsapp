package negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelo.Mensaje;
import org.bson.types.ObjectId;
import persistencia.MensajeDAO;
import persistencia.PersistenciaException;

public class MensajeNegocio implements IMensajeNegocio
{
    MensajeDAO mensajeDAO;
    
    public MensajeNegocio()
    {
        this.mensajeDAO = new MensajeDAO();
    }
    
    @Override
    public List<Mensaje> getAllMensajes() throws NegocioException
    {
        try 
        {
            List<Mensaje> mensajes = this.mensajeDAO.getAllMensajes();
            if (mensajes == null) 
            {
                throw new NegocioException("No existen mensajes registrados");
            }

            return mensajes;
        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws NegocioException
    {
        try 
        {
            List<Mensaje> mensajes = this.mensajeDAO.getMensajesByEmisor(emisor);
            if (mensajes == null) 
            {
                throw new NegocioException("No existen mensajes registrados");
            }

            return mensajes;
        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public Mensaje guardar(String mensaje, ObjectId emisorId) throws NegocioException
    {
        try 
        {
            if (mensaje == null) 
            {
                throw new NegocioException("No se proporciono un mensaje valido");
            }
            
            Mensaje msj = new Mensaje(emisorId, mensaje, LocalTime.now());

            return msj;

        } 
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
}
