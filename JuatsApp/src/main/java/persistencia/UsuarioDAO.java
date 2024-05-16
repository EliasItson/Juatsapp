package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase que maneja las operaciones de persistencia relacionadas con los usuarios.
 */
public class UsuarioDAO implements IUsuarioDAO {

    private final MongoCollection<Usuario> COLECCION;
    private final ConexionBD conexionBD = new ConexionBD();

    /**
     * Constructor de la clase UsuarioDAO.
     */
    public UsuarioDAO() {
        this.COLECCION = conexionBD.getBaseDatos().getCollection("usuarios", Usuario.class);
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de usuarios.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Usuario> getAllUsuarios() throws PersistenciaException {
        List<Usuario> usuarios = new ArrayList<>();
        Bson filter = Filters.eq("isDeleted", false);
        COLECCION.find(filter).into(usuarios);

        return usuarios;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a crear.
     * @return true si se creo el usuario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Boolean createUsuario(Usuario usuario) throws PersistenciaException 
    {
        
        Bson filter = Filters.and(
                Filters.or(
                        Filters.eq("correo", usuario.getCorreo()),
                        Filters.eq("telefono", usuario.getTelefono())
                ),
                Filters.eq("isDeleted", false));
        long existingUsuarios = COLECCION.countDocuments(filter);
    
        if (existingUsuarios == 0) 
        {
            COLECCION.insertOne(usuario);
            return true;
        } 
        else 
            return false;
    }

    /**
     * Valida las credenciales del usuario.
     *
     * @param correo Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return El usuario si las credenciales son válidas, de lo contrario, null.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario validateCredentials(String correo, String password) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.eq("correo", correo)));
            pipeline.add(Aggregates.match(Filters.eq("password", password)));
            pipeline.add(Aggregates.match(Filters.eq("isDeleted", false)));
            return COLECCION.aggregate(pipeline).first();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }

    /**
     * Obtiene un usuario dado su ID.
     *
     * @param usuarioId ID del usuario.
     * @return El usuario encontrado.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.eq("_id", usuarioId)));
            pipeline.add(Aggregates.match(Filters.eq("isDeleted", false)));
            return COLECCION.aggregate(pipeline).first();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }

    /**
     * Obtiene un usuario dado su código.
     *
     * @param codigo El código del usuario.
     * @return El usuario encontrado.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario getUsuarioByCodigo(String codigo) throws PersistenciaException {
        try 
        {
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.regex("codigo", "^" + codigo + "$", "i")));
            pipeline.add(Aggregates.match(Filters.eq("isDeleted", false)));
            return COLECCION.aggregate(pipeline).first();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }

    /**
     * Sube una foto de perfil para un usuario.
     *
     * @param imageData Los datos de la imagen.
     * @param usuario El usuario al que se le subirá la foto de perfil.
     * @return El usuario actualizado.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario uploadPP(byte[] imageData, ObjectId usuario) throws PersistenciaException {
        try {
            Bson filter = Filters.eq("_id", usuario);
            Bson update = Updates.set("foto_perfil", imageData);
            COLECCION.updateOne(filter, update);
            return COLECCION.find(filter).first();

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }

    /**
     * Obtiene un usuario dado su correo electrónico.
     *
     * @param correo El correo electrónico del usuario.
     * @return El usuario encontrado.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario getUsuarioByCorreo(String correo) throws PersistenciaException {
        try {
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(Aggregates.match(Filters.eq("correo", correo)));
            pipeline.add(Aggregates.match(Filters.eq("isDeleted", false)));
            return COLECCION.aggregate(pipeline).first();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException(e.getMessage());
        }
    }
}
