package com.bda.juatsapp;

import java.io.IOException;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.NegocioException;

/**
 * Controlador para la ventana de creación de chat.
 */
public class CrearChatController {

    @FXML
    TextField codigoTxtFld; // Campo de texto para ingresar el código de usuario del chat a crear
    @FXML
    Button crearChatBtn; // Botón para crear el chat
    @FXML
    Label warningCrearChat;

    ChatController controller; // Controlador de la ventana de chat
    Stage crearChatStage; // Etapa de la ventana de creación de chat
    private final Pattern codigoPattern = Pattern.compile("^[0-9A-Fa-f]{6}$");

    /**
     * Inicializa el controlador con el controlador de la ventana de chat y la etapa de la ventana de creación de chat.
     * @param controller El controlador de la ventana de chat.
     * @param crearChatStage La etapa de la ventana de creación de chat.
     */
    public void initData(ChatController controller, Stage crearChatStage) {
        this.controller = controller;
        this.crearChatStage = crearChatStage;
        
        codigoTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!codigoPattern.matcher(newValue).matches()) {
                codigoTxtFld.setStyle("-fx-background-color: #FFC0CB;");
            } else {
                codigoTxtFld.setStyle(""); 
                warningCrearChat.setVisible(false);
            }
        });
    }

    /**
     * Método llamado cuando se hace clic en el botón de creación de chat.
     * Crea un nuevo chat utilizando el código de usuario ingresado y lo agrega a la lista de chats en la ventana de chat principal.
     */
    public void createChat() {
        try
        {
            if (codigoPattern.matcher(codigoTxtFld.getText()).matches()) 
            {
                if(controller.createChat(codigoTxtFld.getText()))
                {  
                    codigoTxtFld.setStyle(""); 
                    warningCrearChat.setVisible(false);
                    codigoTxtFld.clear();
                    return;
                }              
            }
            warningCrearChat.setVisible(true);
            codigoTxtFld.clear();
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}
