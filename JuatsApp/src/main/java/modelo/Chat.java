
package modelo;

import java.util.List;
import org.bson.types.ObjectId;


public class Chat 
{
    private ObjectId id;
    private List<Usuario> miembros;
    private List<Chat> mensajes;
    private Boolean isDeleted;

    public Chat(List<Usuario> miembros, List<Chat> mensajes) 
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

    public List<Chat> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Chat> mensajes) {
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

