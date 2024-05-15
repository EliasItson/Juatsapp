package modelo;

import java.time.LocalDate;
import org.bson.types.ObjectId;


public class Usuario 
{
    private ObjectId id;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String sexo;
    private String codigo;
    private byte[] foto_perfil;
    private Boolean isDeleted;

    public Usuario() 
    {
        this.isDeleted = false;
    }
    
    public Usuario(String nombre, String correo, String password, String telefono, LocalDate fechaNacimiento, String sexo, String codigo) 
    {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.fecha_nacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.codigo = codigo;
        this.isDeleted = false;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public byte[] getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(byte[] foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + ", sexo=" + sexo + ", codigo=" + codigo + ", isDeleted=" + isDeleted + '}';
    }
    
    
    
    
    
}
