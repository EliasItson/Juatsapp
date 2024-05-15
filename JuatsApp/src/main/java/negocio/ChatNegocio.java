package negocio;

import java.util.ArrayList;
import java.util.List;
import modelo.Chat;
import modelo.Mensaje;
import modelo.Usuario;
import org.bson.types.ObjectId;
import persistencia.ChatDAO;
import persistencia.PersistenciaException;

public class ChatNegocio {
    
    ChatDAO chatDAO;
    UsuarioNegocio usuarioNegocio;
    
    public ChatNegocio() 
    {
        this.chatDAO = new ChatDAO();
        this.usuarioNegocio = new UsuarioNegocio();
    }
    
    public List<Chat> getAllChats() throws NegocioException
    {
        try 
        {
            List<Chat> chats = this.chatDAO.getAllChats();
            if (chats == null) 
            {
                throw new NegocioException("No existen usuarios registrados");
            }

            return chats;
        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    public void createChat(Usuario usuario1, String codigo) throws NegocioException
    {       
        try 
        {
            Usuario usuario2 = usuarioNegocio.getUsuarioByCodigo(codigo);
            List<Usuario> miembros = new ArrayList<>();
            miembros.add(usuario1);
            miembros.add(usuario2);
            
            Chat nuevoChat = new Chat(miembros);
            
            chatDAO.createChat(nuevoChat);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    public List<Chat> getChatsByUsuario(ObjectId usuario) throws NegocioException
    {
        try 
        {
            
            List<Chat> chats = chatDAO.getChatsByUsuario(usuario);
            return chats;

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    public Chat getChatById(ObjectId chat) throws NegocioException
    {
        try 
        {
            return chatDAO.getChatById(chat);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    public void updateChat(Chat chat, Mensaje mensaje) throws NegocioException
    {
        try 
        {
            chatDAO.updateChat(chat, mensaje);

        } 
        catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
    
    public void deleteChat(ObjectId chat) throws NegocioException
    {
        try
        {
            chatDAO.deleteChat(chat);
        }
        catch(PersistenciaException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
