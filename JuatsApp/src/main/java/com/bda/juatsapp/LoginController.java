package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import org.bson.types.ObjectId;


public class LoginController implements Initializable
{
    @FXML
    private TextField correoUsuarioTxtFld;
    @FXML
    private PasswordField passUsuarioPassFld;
    @FXML
    private Button ingresarBtn;
    @FXML
    private Button switchToSignupBtn;
    
    private UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    public void ingresar()
    {
        try
        {
            String correo = correoUsuarioTxtFld.getText();
            String password = passUsuarioPassFld.getText();
            ObjectId loggedInUserId = usuarioNegocio.validateCredentials(correo, password);
            FXMLLoader loader = App.setRoot("chat");
            ChatController controller = loader.getController();
            controller.initData(loggedInUserId);
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void switchToSignup() throws IOException
    {
        App.setRoot("signup");
    }
}
