package com.bda.juatsapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearChatController 
{

    @FXML
    TextField codigoTxtFld;
    @FXML
    Button crearChatBtn;
    
    ChatController controller;
    Stage crearChatStage;
    
    public void initData(ChatController controller, Stage crearChatStage)
    {
        this.controller = controller;
        this.crearChatStage = crearChatStage;
        
    }
    
    public void createChat()
    {
        if(!codigoTxtFld.getText().isBlank())
        {
            controller.createChat(codigoTxtFld.getText());
        }
    }
}