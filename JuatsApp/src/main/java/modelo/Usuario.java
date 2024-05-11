package modelo;

import org.bson.types.ObjectId;


public class Usuario 
{
    private ObjectId id;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private String fechaNacimiento;
    private String sexo;
    private ImagenMetadata fotoPerfil;
    private Boolean isDeleted;
    
    public Usuario(String nombre, String correo, String password, String telefono, String fechaNacimiento, String sexo) 
    {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public ImagenMetadata getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(ImagenMetadata fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
     @Override
    public String toString() 
    {
        return "Usuario{" + "nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + '}';
    }
    
    
}
