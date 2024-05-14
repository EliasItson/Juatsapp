package modelo;

import java.time.LocalTime;
import org.bson.types.ObjectId;


public class Mensaje 
{
    private ObjectId id;
    private ObjectId emisorId;
    private String textoMensaje;
    private ImagenMetadata imagen;
    private LocalTime fechaTiempoEnvio;
    private Boolean isDeleted;

    public Mensaje() 
    {
        this.isDeleted = false;
    }

    
    
    public Mensaje(ObjectId emisorId, String textoMensaje, LocalTime fechaTiempoEnvio) 
    {
        this.emisorId = emisorId;
        this.textoMensaje = textoMensaje;
        this.fechaTiempoEnvio = fechaTiempoEnvio;
        this.isDeleted = false;
    }
    
    public Mensaje(ObjectId emisorId, ImagenMetadata imagen, LocalTime fechaTiempoEnvio) 
    {
        this.emisorId = emisorId;
        this.imagen = imagen;
        this.fechaTiempoEnvio = fechaTiempoEnvio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(ObjectId emisorId) {
        this.emisorId = emisorId;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public ImagenMetadata getImagen() 
    {
        return imagen;
    }

    public void setImagen(ImagenMetadata imagen) 
    {
        this.imagen = imagen;
    }

    public LocalTime getFechaTiempoEnvio() {
        return fechaTiempoEnvio;
    }

    public void setFechaTiempoEnvio(LocalTime fechaTiempoEnvio) {
        this.fechaTiempoEnvio = fechaTiempoEnvio;
    }
    
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() 
    {
        return "Chat{" + "emisorId=" + emisorId + ", contenido=" + textoMensaje + ", fechaTiempoEnvio=" + fechaTiempoEnvio + '}';
    }
    
}
