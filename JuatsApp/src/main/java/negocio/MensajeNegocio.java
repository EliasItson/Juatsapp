package negocio;

import java.time.LocalTime;
import java.util.List;
import modelo.Mensaje;
import org.bson.types.ObjectId;
import persistencia.MensajeDAO;
import persistencia.PersistenciaException;

/**
 * Clase que maneja la lógica de negocio relacionada con los mensajes.
 */
public class MensajeNegocio implements IMensajeNegocio {

    private MensajeDAO mensajeDAO;

    /**
     * Constructor de la clase MensajeNegocio.
     */
    public MensajeNegocio() {
        this.mensajeDAO = new MensajeDAO();
    }

    /**
     * Obtiene todos los mensajes.
     *
     * @return Lista de mensajes.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public List<Mensaje> getAllMensajes() throws NegocioException {
        try {
            List<Mensaje> mensajes = this.mensajeDAO.getAllMensajes();
            if (mensajes == null) {
                throw new NegocioException("No existen mensajes registrados");
            }
            return mensajes;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene los mensajes enviados por un emisor.
     *
     * @param emisor ID del emisor.
     * @return Lista de mensajes enviados por el emisor.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public List<Mensaje> getMensajesByEmisor(ObjectId emisor) throws NegocioException {
        try {
            List<Mensaje> mensajes = this.mensajeDAO.getMensajesByEmisor(emisor);
            if (mensajes == null) {
                throw new NegocioException("No existen mensajes registrados");
            }
            return mensajes;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Guarda un nuevo mensaje.
     *
     * @param mensaje   Contenido del mensaje.
     * @param emisorId  ID del emisor del mensaje.
     * @return Mensaje guardado.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Mensaje guardar(String mensaje, ObjectId emisorId) throws NegocioException {
        try {
            if (mensaje == null) {
                throw new NegocioException("No se proporcionó un mensaje válido");
            }

            Mensaje msj = new Mensaje(emisorId, mensaje, LocalTime.now());

            mensajeDAO.guardar(msj);
            
            return msj;

        } catch (NullPointerException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    /**
     * Guarda un nuevo mensaje con imagen.
     *
     * @param imagen   imagen a enviar.
     * @param emisorId  ID del emisor del mensaje.
     * @return Mensaje guardado.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Mensaje guardar(byte[] imagen, ObjectId emisorId) throws NegocioException {
        try {
            if (imagen == null) {
                throw new NegocioException("No se proporcionó un mensaje válido");
            }

            Mensaje msj = new Mensaje(emisorId, imagen, LocalTime.now());

            mensajeDAO.guardar(msj);
            
            return msj;

        } catch (NullPointerException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
}
