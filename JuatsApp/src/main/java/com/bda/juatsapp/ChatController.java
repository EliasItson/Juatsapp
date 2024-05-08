package com.bda.juatsapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class ChatController 
{
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
