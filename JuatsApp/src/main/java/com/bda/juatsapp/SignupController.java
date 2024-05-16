package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    private Label warningNombre;
    @FXML
    private Label warningCorreo;
    @FXML
    private Label warningPass;
    @FXML
    private Label warningTelefono;
    @FXML
    private Label warningSignup;
    

    private final UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
    private final Pattern namePattern = Pattern.compile("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:\\s[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$");
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private final Pattern passPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,])[A-Za-z\\d@$!%*?&.,]{8,16}$");
    private final Pattern numPattern = Pattern.compile("^\\d{10}$");
    private final Pattern fechaPattern = Pattern.compile("^(1[0-2]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])/\\d{4}$");
    
    
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
        
        nombreUsuarioTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!namePattern.matcher(newValue).matches()) {
                nombreUsuarioTxtFld.setStyle("-fx-background-color: #FFC0CB;");
                warningNombre.setVisible(true);
            } else {
                nombreUsuarioTxtFld.setStyle(""); 
                warningNombre.setVisible(false);
            }
        });
        
        correoUsuarioTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!emailPattern.matcher(newValue).matches()) {
                correoUsuarioTxtFld.setStyle("-fx-background-color: #FFC0CB;");
                warningCorreo.setVisible(true);
            } else {
                correoUsuarioTxtFld.setStyle(""); 
                warningCorreo.setVisible(false);
            }
        });
        
        passUsuarioPassFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!passPattern.matcher(newValue).matches()) {
                passUsuarioPassFld.setStyle("-fx-background-color: #FFC0CB;");
                warningPass.setVisible(true);
            } else {
                passUsuarioPassFld.setStyle(""); 
                warningPass.setVisible(false);
            }
        });
        
        telefonoUsuarioTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!numPattern.matcher(newValue).matches()) {
                telefonoUsuarioTxtFld.setStyle("-fx-background-color: #FFC0CB;");
                warningTelefono.setVisible(true);
            } else {
                telefonoUsuarioTxtFld.setStyle("");
                warningTelefono.setVisible(false);
            }
        });
        
    }

    /**
     * Método para registrar una nueva cuenta de usuario.
     *
     * @throws IOException Si ocurre un error al cargar la vista de inicio de sesión.
     */
    public void registrarCuenta() throws IOException 
    {
        try 
        {
            if(fechaUsuarioDatePicker.getValue() != null)
            {
                String formattedDate = fechaUsuarioDatePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if(namePattern.matcher(nombreUsuarioTxtFld.getText()).matches()
                    && emailPattern.matcher(correoUsuarioTxtFld.getText()).matches()
                    && passPattern.matcher(passUsuarioPassFld.getText()).matches()
                    && numPattern.matcher(telefonoUsuarioTxtFld.getText()).matches()
                    && fechaPattern.matcher(formattedDate).matches())
                {   
                    String nombre = nombreUsuarioTxtFld.getText();
                    String correo = correoUsuarioTxtFld.getText();
                    String salt = BCrypt.gensalt();
                    String hashedPassword = BCrypt.hashpw(passUsuarioPassFld.getText(), salt);
                    String telefono = telefonoUsuarioTxtFld.getText();
                    LocalDate fechaNacimiento = fechaUsuarioDatePicker.getValue();
                    String sexo = sexoUsuarioCmbBox.getValue();
                    if(usuarioNegocio.createUsuario(nombre, correo, hashedPassword, salt, telefono, fechaNacimiento, sexo))
                    {
                        warningSignup.setVisible(false);
                        App.setRoot("login");
                    }
                }
            }
            nombreUsuarioTxtFld.clear();
            correoUsuarioTxtFld.clear();
            passUsuarioPassFld.clear();
            telefonoUsuarioTxtFld.clear();
            fechaUsuarioDatePicker.setValue(null);
            sexoUsuarioCmbBox.setValue(sexoUsuarioCmbBox.getItems().get(0));
            warningSignup.setVisible(true); 
        } 
        catch(NegocioException e) 
        {
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
