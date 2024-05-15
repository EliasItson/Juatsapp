package modelo;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 * Representa un usuario en el sistema.
 */
public class Usuario 
{
    private ObjectId id;
    private String nombre;
    private String correo;
    private String password;
    private String salt;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String sexo;
    private String codigo;
    private byte[] foto_perfil;
    private Boolean isDeleted;

    /**
     * Crea un nuevo usuario con los valores predeterminados.
     */
    public Usuario() 
    {
        this.isDeleted = false;
    }
    
    /**
     * Crea un nuevo usuario con los detalles especificados.
     * @param nombre El nombre del usuario.
     * @param correo El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @param salt El valor de sal para la contraseña.
     * @param telefono El número de teléfono del usuario.
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     * @param sexo El sexo del usuario.
     * @param codigo El código único del usuario.
     */
    public Usuario(String nombre, String correo, String password, String salt, String telefono, LocalDate fechaNacimiento, String sexo, String codigo) 
    {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.salt = salt;
        this.telefono = telefono;
        this.fecha_nacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.codigo = codigo;
        this.isDeleted = false;
    }

    /**
     * Devuelve el identificador único del usuario.
     * @return El ObjectId del usuario.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * @param id El ObjectId a establecer.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del usuario.
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param correo El correo electrónico a establecer.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el número de teléfono del usuario.
     * @return El número de teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * @param telefono El número de teléfono a establecer.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     * @return La fecha de nacimiento del usuario.
     */
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     * @param fecha_nacimiento La fecha de nacimiento a establecer.
     */
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Devuelve el sexo del usuario.
     * @return El sexo del usuario.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del usuario.
     * @param sexo El sexo a establecer.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Devuelve el código único del usuario.
     * @return El código único del usuario.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código único del usuario.
     * @param codigo El código único a establecer.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Devuelve los datos de la foto de perfil del usuario.
     * @return Los datos de la foto de perfil del usuario.
     */
    public byte[] getFoto_perfil() {
        return foto_perfil;
    }

    /**
     * Establece los datos de la foto de perfil del usuario.
     * @param foto_perfil Los datos de la foto de perfil a establecer.
     */
    public void setFoto_perfil(byte[] foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    /**
     * Devuelve un booleano que indica si el usuario está eliminado o no.
     * @return True si el usuario está eliminado, false de lo contrario.
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Establece el estado eliminado del usuario.
     * @param isDeleted El valor booleano a establecer.
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Devuelve el valor de sal utilizado para la contraseña del usuario.
     * @return El valor de sal del usuario.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Establece el valor de sal utilizado para la contraseña del usuario.
     * @param salt El valor de sal a establecer.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    /**
     * Devuelve una representación en cadena del usuario.
     * @return Una cadena que contiene información sobre el usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + ", sexo=" + sexo + ", codigo=" + codigo + ", isDeleted=" + isDeleted + '}';
    }
}
