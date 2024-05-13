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

public class SignupController implements Initializable
{
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        sexoUsuarioCmbBox.setValue("Hombre");
        sexoUsuarioCmbBox.getItems().addAll("Hombre", "Mujer", "Gato", "Perro", "No Binario");
    }
    
    public void registrarCuenta()
    {
        try
        {
            String nombre = nombreUsuarioTxtFld.getText();
            String correo = correoUsuarioTxtFld.getText();
            String password = passUsuarioPassFld.getText();
            String telefono = telefonoUsuarioTxtFld.getText();
            LocalDate fechaNacimiento = fechaUsuarioDatePicker.getValue();
            String sexo = sexoUsuarioCmbBox.getValue();
            usuarioNegocio.createUsuario(nombre, correo, password, telefono, fechaNacimiento, sexo);
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void switchToLogin() throws IOException
    {
        App.setRoot("login");
    }
}