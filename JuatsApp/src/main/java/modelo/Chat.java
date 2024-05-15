
package modelo;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;


/**
 * Representa un chat entre varios usuarios.
 */
public class Chat 
{
    private ObjectId id;
    private List<Usuario> miembros;
    private List<Mensaje> mensajes;
    private Boolean isDeleted;

    /**
     * Construye un nuevo chat vacío.
     */
    public Chat()
    {
        
    }
    
    /**
     * Construye un nuevo chat con la lista de miembros dada.
     * @param miembros La lista de miembros que participan en el chat.
     */
    public Chat(List<Usuario> miembros) 
    {
        this.miembros = miembros;
        this.mensajes = new ArrayList<>();
        this.isDeleted = false;
    }

    /**
     * Devuelve el identificador único del chat.
     * @return El ObjectId del chat.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del chat.
     * @param id El ObjectId a establecer.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Devuelve la lista de miembros que participan en el chat.
     * @return La lista de usuarios.
     */
    public List<Usuario> getMiembros() {
        return miembros;
    }

    /**
     * Establece la lista de miembros que participan en el chat.
     * @param miembros La lista de usuarios a establecer.
     */
    public void setMiembros(List<Usuario> miembros) {
        this.miembros = miembros;
    }

    /**
     * Devuelve la lista de mensajes en el chat.
     * @return La lista de mensajes.
     */
    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    /**
     * Establece la lista de mensajes en el chat.
     * @param mensajes La lista de mensajes a establecer.
     */
    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    /**
     * Devuelve un booleano que indica si el chat está eliminado o no.
     * @return True si el chat está eliminado, false de lo contrario.
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Establece el estado eliminado del chat.
     * @param isDeleted El valor booleano a establecer.
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Devuelve una representación en cadena del chat.
     * @return Una cadena que contiene información sobre el chat.
     */
    @Override
    public String toString() 
    {
        return "Mensaje{" + "miembros=" + miembros + ", mensajes=" + mensajes + '}';
    }
    
    
}

