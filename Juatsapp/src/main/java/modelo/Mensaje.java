package modelo;

import java.util.List;
import org.bson.types.ObjectId;


public class Mensaje 
{
    private ObjectId id;
    private List<Usuario> miembros;
    private List<Mensaje> mensajes;

    public Mensaje(List<Usuario> miembros, List<Mensaje> mensajes) 
    {
        this.miembros = miembros;
        this.mensajes = mensajes;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Usuario> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Usuario> miembros) {
        this.miembros = miembros;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() 
    {
        return "Mensaje{" + "miembros=" + miembros + ", mensajes=" + mensajes + '}';
    }
    
    
}
