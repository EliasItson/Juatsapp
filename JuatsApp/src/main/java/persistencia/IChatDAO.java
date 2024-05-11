package persistencia;

import java.util.List;
import modelo.Mensaje;

public interface IChatDAO 
{
    public List<Mensaje> consultar() throws PersistenciaException;
    public void guardar(Mensaje chat) throws PersistenciaException;
}
