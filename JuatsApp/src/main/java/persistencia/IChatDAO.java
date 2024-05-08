package persistencia;

import java.util.List;
import modelo.Chat;

public interface IChatDAO 
{
    public List<Chat> consultar() throws PersistenciaException;
    public void guardar(Chat chat) throws PersistenciaException;
}
