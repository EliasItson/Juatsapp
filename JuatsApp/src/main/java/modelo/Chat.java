
package modelo;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;


public class Chat 
{
    private ObjectId id;
    private List<Usuario> miembros;
    private List<Mensaje> mensajes;
    private Boolean isDeleted;

    public Chat()
    {
        
    }
    
    public Chat(List<Usuario> miembros) 
    {
        this.miembros = miembros;
        this.mensajes = new ArrayList<>();
        this.isDeleted = false;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() 
    {
        return "Mensaje{" + "miembros=" + miembros + ", mensajes=" + mensajes + '}';
    }
    
    
}

