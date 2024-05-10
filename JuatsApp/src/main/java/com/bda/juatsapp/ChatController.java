package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ChatController implements Initializable 
{

    @FXML
    private Circle userPP;
    @FXML
    private Label userDisplayName;
    @FXML
    private ImageView userMenu; 

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        URL imageUrl = getClass().getResource("/media/vera.png");
        if (imageUrl != null) {
            Image img = new Image(imageUrl.toExternalForm());
            userPP.setFill(new ImagePattern(img));
        } else {
            System.out.println("Image resource not found.");
        }
        
        userDisplayName.setText("Jorge Elias");
        
        URL imageUrl2 = getClass().getResource("/media/Menu.png");
        if (imageUrl2 != null) {
            Image img = new Image(imageUrl2.toExternalForm());
            userMenu.setImage(img);
        } else {
            System.out.println("Image resource not found.");
        }
        
    }
}
