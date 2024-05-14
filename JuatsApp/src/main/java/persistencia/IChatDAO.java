package persistencia;

import java.util.List;
import modelo.Chat;

public interface IChatDAO 
{
    public List<Chat> getAllChats() throws PersistenciaException;
    public void createChat(Chat chat) throws PersistenciaException;
}
