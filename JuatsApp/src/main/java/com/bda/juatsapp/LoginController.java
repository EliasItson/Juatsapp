package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Usuario;
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
            Usuario loggedInUser = usuarioNegocio.validateCredentials(correo, password);
            FXMLLoader loader = App.setRoot("chat");
            ChatController controller = loader.getController();
            System.out.println(loggedInUser.toString());
            controller.initData(loggedInUser);
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
