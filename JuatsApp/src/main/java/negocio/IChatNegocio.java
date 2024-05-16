package negocio;

import java.util.ArrayList;
import java.util.List;
import modelo.Chat;
import modelo.Mensaje;
import modelo.Usuario;
import org.bson.types.ObjectId;
import persistencia.PersistenciaException;


public interface IChatNegocio 
{
    public List<Chat> getAllChats() throws NegocioException;
    public boolean createChat(Usuario usuario1, String codigo) throws NegocioException;
    public List<Chat> getChatsByUsuario(ObjectId usuario) throws NegocioException;
    public Chat getChatById(ObjectId chat) throws NegocioException;
    public void updateChat(Chat chat, Mensaje mensaje) throws NegocioException;
    public void deleteChat(ObjectId chat) throws NegocioException;

}
