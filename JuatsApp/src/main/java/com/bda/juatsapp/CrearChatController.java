package com.bda.juatsapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de creación de chat.
 */
public class CrearChatController {

    @FXML
    TextField codigoTxtFld; // Campo de texto para ingresar el código de usuario del chat a crear
    @FXML
    Button crearChatBtn; // Botón para crear el chat

    ChatController controller; // Controlador de la ventana de chat
    Stage crearChatStage; // Etapa de la ventana de creación de chat

    /**
     * Inicializa el controlador con el controlador de la ventana de chat y la etapa de la ventana de creación de chat.
     * @param controller El controlador de la ventana de chat.
     * @param crearChatStage La etapa de la ventana de creación de chat.
     */
    public void initData(ChatController controller, Stage crearChatStage) {
        this.controller = controller;
        this.crearChatStage = crearChatStage;
    }

    /**
     * Método llamado cuando se hace clic en el botón de creación de chat.
     * Crea un nuevo chat utilizando el código de usuario ingresado y lo agrega a la lista de chats en la ventana de chat principal.
     */
    public void createChat() {
        if (!codigoTxtFld.getText().isBlank()) {
            controller.createChat(codigoTxtFld.getText());
        }
    }
}
