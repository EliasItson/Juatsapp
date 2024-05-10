package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ChatController implements Initializable {

    @FXML
    private Circle userPP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        URL imageUrl = getClass().getClassLoader().getResource("media/yata.jpg");
        if (imageUrl != null) {
            Image img = new Image(imageUrl.toExternalForm());
            userPP.setFill(new ImagePattern(img));
        } else {
            System.out.println("Image resource not found.");
        }

    }
}
