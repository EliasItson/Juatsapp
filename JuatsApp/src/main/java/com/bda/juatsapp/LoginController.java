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

/**
 * Controlador para la vista de inicio de sesión.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField correoUsuarioTxtFld;
    @FXML
    private PasswordField passUsuarioPassFld;
    @FXML
    private Button ingresarBtn;
    @FXML
    private Button switchToSignupBtn;

    private UsuarioNegocio usuarioNegocio = new UsuarioNegocio();

    /**
     * Inicializa el controlador después de que su raíz de vista haya sido completamente procesada.
     *
     * @param url El localizador de recursos utilizado para encontrar la raíz del objeto.
     * @param rb  El recurso de paquetes utilizado para localizar objetos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método para manejar el evento de inicio de sesión.
     */
    public void ingresar() {
        try {
            String correo = correoUsuarioTxtFld.getText();
            String password = passUsuarioPassFld.getText();
            Usuario loggedInUser = usuarioNegocio.validateCredentials(correo, password);
            if (loggedInUser != null) {
                FXMLLoader loader = App.setRoot("chat");
                ChatController controller = loader.getController();
                controller.initData(loggedInUser, usuarioNegocio);
            }
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para cambiar a la vista de registro.
     *
     * @throws IOException Si ocurre un error al cargar la vista de registro.
     */
    public void switchToSignup() throws IOException {
        App.setRoot("signup");
    }
}
