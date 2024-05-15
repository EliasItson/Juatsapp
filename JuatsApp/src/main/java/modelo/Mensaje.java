package modelo;

import java.time.LocalTime;
import org.bson.types.ObjectId;

/**
 * Representa un mensaje enviado dentro de un chat.
 */
public class Mensaje 
{
    private ObjectId id;
    private ObjectId emisorId;
    private String textoMensaje;
    private byte[] imagen;
    private LocalTime fechaTiempoEnvio;
    private Boolean isDeleted;

    /**
     * Crea un nuevo mensaje sin contenido ni imagen, con la hora de envío actual.
     */
    public Mensaje() 
    {
        this.isDeleted = false;
    }

    /**
     * Crea un nuevo mensaje con contenido de texto, sin imagen, y la hora de envío actual.
     * @param emisorId El identificador del emisor del mensaje.
     * @param textoMensaje El texto del mensaje.
     * @param fechaTiempoEnvio La hora de envío del mensaje.
     */
    public Mensaje(ObjectId emisorId, String textoMensaje, LocalTime fechaTiempoEnvio) 
    {
        this.emisorId = emisorId;
        this.textoMensaje = textoMensaje;
        this.fechaTiempoEnvio = fechaTiempoEnvio;
        this.isDeleted = false;
    }
    
    /**
     * Crea un nuevo mensaje con una imagen adjunta, sin contenido de texto, y la hora de envío actual.
     * @param emisorId El identificador del emisor del mensaje.
     * @param imagen Los datos de la imagen adjunta.
     * @param fechaTiempoEnvio La hora de envío del mensaje.
     */
    public Mensaje(ObjectId emisorId, byte[] imagen, LocalTime fechaTiempoEnvio) 
    {
        this.emisorId = emisorId;
        this.imagen = imagen;
        this.fechaTiempoEnvio = fechaTiempoEnvio;
    }

    /**
     * Devuelve el identificador único del mensaje.
     * @return El ObjectId del mensaje.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del mensaje.
     * @param id El ObjectId a establecer.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Devuelve el identificador del emisor del mensaje.
     * @return El ObjectId del emisor del mensaje.
     */
    public ObjectId getEmisorId() {
        return emisorId;
    }

    /**
     * Establece el identificador del emisor del mensaje.
     * @param emisorId El ObjectId del emisor del mensaje.
     */
    public void setEmisorId(ObjectId emisorId) {
        this.emisorId = emisorId;
    }

    /**
     * Devuelve el texto del mensaje.
     * @return El texto del mensaje.
     */
    public String getTextoMensaje() {
        return textoMensaje;
    }

    /**
     * Establece el texto del mensaje.
     * @param textoMensaje El texto a establecer.
     */
    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    /**
     * Devuelve los datos de la imagen adjunta al mensaje.
     * @return Los datos de la imagen.
     */
    public byte[] getImagen() 
    {
        return imagen;
    }

    /**
     * Establece los datos de la imagen adjunta al mensaje.
     * @param imagen Los datos de la imagen a establecer.
     */
    public void setImagen(byte[] imagen) 
    {
        this.imagen = imagen;
    }

    /**
     * Devuelve la hora de envío del mensaje.
     * @return La hora de envío del mensaje.
     */
    public LocalTime getFechaTiempoEnvio() {
        return fechaTiempoEnvio;
    }

    /**
     * Establece la hora de envío del mensaje.
     * @param fechaTiempoEnvio La hora de envío a establecer.
     */
    public void setFechaTiempoEnvio(LocalTime fechaTiempoEnvio) {
        this.fechaTiempoEnvio = fechaTiempoEnvio;
    }
    
    /**
     * Devuelve un booleano que indica si el mensaje está eliminado o no.
     * @return True si el mensaje está eliminado, false de lo contrario.
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Establece el estado eliminado del mensaje.
     * @param isDeleted El valor booleano a establecer.
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Devuelve una representación en cadena del mensaje.
     * @return Una cadena que contiene información sobre el mensaje.
     */
    @Override
    public String toString() 
    {
        return "Chat{" + "emisorId=" + emisorId + ", contenido=" + textoMensaje + ", fechaTiempoEnvio=" + fechaTiempoEnvio + '}';
    }
    
}
