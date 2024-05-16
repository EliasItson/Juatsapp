package negocio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import modelo.Usuario;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;
import persistencia.PersistenciaException;
import persistencia.UsuarioDAO;

/**
 * Clase que maneja la lógica de negocio relacionada con los usuarios.
 */
public class UsuarioNegocio implements IUsuarioNegocio {

    private UsuarioDAO usuarioDAO;

    /**
     * Constructor de la clase UsuarioNegocio.
     */
    public UsuarioNegocio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de usuarios.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public List<Usuario> getAllUsuarios() throws NegocioException {
        try {
            List<Usuario> usuarios = this.usuarioDAO.getAllUsuarios();
            if (usuarios == null) {
                throw new NegocioException("No existen usuarios registrados");
            }
            return usuarios;
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param nombre          Nombre del usuario.
     * @param correo          Correo electrónico del usuario.
     * @param password        Contraseña del usuario.
     * @param salt            Salt para el hash de la contraseña.
     * @param telefono        Número de teléfono del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param sexo            Género del usuario.
     * @return true si se creao el usuario.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Boolean createUsuario(String nombre, String correo, String password, String salt, String telefono, LocalDate fechaNacimiento, String sexo) throws NegocioException 
    {
        Random random = new Random();
        int randomNumber = random.nextInt(16777216);
        String codigo = String.format("%06X", randomNumber);

        try 
        {
            Usuario usuario = new Usuario(nombre, correo, password, salt, telefono, fechaNacimiento, sexo, codigo);
            
            if(fechaNacimiento.isAfter(LocalDate.now()))
                return false;
            return usuarioDAO.createUsuario(usuario);

        } catch (PersistenciaException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (NullPointerException e) 
        {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param correo   Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return El usuario si las credenciales son válidas, o null en caso contrario.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario validateCredentials(String correo, String password) throws NegocioException {
        try {
            Usuario usuario = usuarioDAO.getUsuarioByCorreo(correo);
            if (BCrypt.checkpw(password, usuario.getPassword()))
                return usuario;
            else
                return null;

        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene un usuario con información recortada.
     *
     * @param usuarioId ID del usuario.
     * @return El usuario con información recortada.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario getTrimmedUsuarioById(ObjectId usuarioId) throws NegocioException {
        try {

            return usuarioDAO.getTrimmedUsuarioById(usuarioId);

        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene un usuario por su código.
     *
     * @param codigo Código del usuario.
     * @return El usuario correspondiente al código.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario getUsuarioByCodigo(String codigo) throws NegocioException {
        try {
            return usuarioDAO.getUsuarioByCodigo(codigo);

        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Sube una imagen de perfil para un usuario.
     *
     * @param imageFile Archivo de imagen.
     * @param usuario   ID del usuario.
     * @return El usuario con la imagen de perfil actualizada.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public Usuario uploadPP(File imageFile, ObjectId usuario) throws NegocioException {
        try {
            byte[] imageData = Files.readAllBytes(imageFile.toPath());
            return usuarioDAO.uploadPP(imageData, usuario);
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new NegocioException(e.getMessage());
        }
    }
}
