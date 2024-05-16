package negocio;

import java.util.ArrayList;
import java.util.List;
import modelo.Chat;
import modelo.Mensaje;
import modelo.Usuario;
import org.bson.types.ObjectId;
import persistencia.ChatDAO;
import persistencia.PersistenciaException;

/**
 * Clase que maneja la lógica de negocio relacionada con los chats.
 */
public class ChatNegocio implements IChatNegocio {

    private ChatDAO chatDAO;
    private UsuarioNegocio usuarioNegocio;

    /**
     * Constructor de la clase ChatNegocio.
     */
    public ChatNegocio() {
        this.chatDAO = new ChatDAO();
        this.usuarioNegocio = new UsuarioNegocio();
    }

    /**
     * Obtiene todos los chats.
     *
     * @return Lista de chats.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public List<Chat> getAllChats() throws NegocioException {
        try {
            List<Chat> chats = this.chatDAO.getAllChats();
            if (chats == null) {
                throw new NegocioException("No existen usuarios registrados");
            }
            return chats;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo chat.
     *
     * @param usuario1 Usuario que crea el chat.
     * @param codigo   Código del segundo usuario en el chat.
     * @return true si se creo el chat
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public boolean createChat(Usuario usuario1, String codigo) throws NegocioException 
    {
        try 
        {
            
            Usuario usuario2 = usuarioNegocio.getUsuarioByCodigo(codigo);
            if(usuario2 == null)
                return false;
            List<Usuario> miembros = new ArrayList<>();
            miembros.add(usuario1);
            miembros.add(usuario2);
            Chat nuevoChat = new Chat(miembros);
            return chatDAO.createChat(nuevoChat);
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene los chats asociados a un usuario.
     *
     * @param usuario ID del usuario.
     * @return Lista de chats asociados al usuario.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public List<Chat> getChatsByUsuario(ObjectId usuario) throws NegocioException {
        try {
            List<Chat> chats = chatDAO.getChatsByUsuario(usuario);
            return chats;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene un chat por su ID.
     *
     * @param chat ID del chat.
     * @return Chat correspondiente al ID.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Chat getChatById(ObjectId chat) throws NegocioException {
        try {
            return chatDAO.getChatById(chat);
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Actualiza un chat con un nuevo mensaje.
     *
     * @param chat    Chat a actualizar.
     * @param mensaje Nuevo mensaje a agregar al chat.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public void updateChat(Chat chat, Mensaje mensaje) throws NegocioException {
        try {
            chatDAO.updateChat(chat, mensaje);
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Elimina un chat por su ID.
     *
     * @param chat ID del chat a eliminar.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public void deleteChat(ObjectId chat) throws NegocioException {
        try {
            chatDAO.deleteChat(chat);
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
}
