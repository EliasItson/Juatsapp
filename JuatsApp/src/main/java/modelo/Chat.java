package modelo;

import java.time.LocalTime;
import org.bson.types.ObjectId;


public class Chat 
{
    private ObjectId id;
    private ObjectId emisorId;
    private ObjectId receptorId;
    private String contenido;
    private LocalTime fechaTiempoEnvio;

    public Chat(ObjectId emisorId, ObjectId receptorId, String contenido, LocalTime fechaTiempoEnvio) 
    {
        this.emisorId = emisorId;
        this.receptorId = receptorId;
        this.contenido = contenido;
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

    public ObjectId getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(ObjectId receptorId) {
        this.receptorId = receptorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalTime getFechaTiempoEnvio() {
        return fechaTiempoEnvio;
    }

    public void setFechaTiempoEnvio(LocalTime fechaTiempoEnvio) {
        this.fechaTiempoEnvio = fechaTiempoEnvio;
    }

    @Override
    public String toString() 
    {
        return "Chat{" + "emisorId=" + emisorId + ", receptorId=" + receptorId + ", contenido=" + contenido + ", fechaTiempoEnvio=" + fechaTiempoEnvio + '}';
    }
    
    
    
    
}
