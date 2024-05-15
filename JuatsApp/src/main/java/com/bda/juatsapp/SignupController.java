package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Controlador para la vista de registro de usuario.
 */
public class SignupController implements Initializable {

    @FXML
    private TextField nombreUsuarioTxtFld;
    @FXML
    private TextField correoUsuarioTxtFld;
    @FXML
    private PasswordField passUsuarioPassFld;
    @FXML
    private TextField telefonoUsuarioTxtFld;
    @FXML
    private DatePicker fechaUsuarioDatePicker;
    @FXML
    private ComboBox<String> sexoUsuarioCmbBox;
    @FXML
    private Button registrarBtn;
    @FXML
    private Button moveToLoginBtn;

    private UsuarioNegocio usuarioNegocio = new UsuarioNegocio();

    /**
     * Inicializa el controlador después de que su raíz de vista haya sido completamente procesada.
     *
     * @param url El localizador de recursos utilizado para encontrar la raíz del objeto.
     * @param rb  El recurso de paquetes utilizado para localizar objetos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexoUsuarioCmbBox.setValue("Hombre");
        sexoUsuarioCmbBox.getItems().addAll("Hombre", "Mujer", "Gato", "Perro", "No Binario");
    }

    /**
     * Método para registrar una nueva cuenta de usuario.
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio de sesión.
     */
    public void registrarCuenta() throws IOException {
        try {
            String nombre = nombreUsuarioTxtFld.getText();
            String correo = correoUsuarioTxtFld.getText();
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(passUsuarioPassFld.getText(), salt);
            String telefono = telefonoUsuarioTxtFld.getText();
            LocalDate fechaNacimiento = fechaUsuarioDatePicker.getValue();
            String sexo = sexoUsuarioCmbBox.getValue();
            usuarioNegocio.createUsuario(nombre, correo, hashedPassword, salt, telefono, fechaNacimiento, sexo);
            App.setRoot("login");
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para cambiar a la vista de inicio de sesión.
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio de sesión.
     */
    public void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
