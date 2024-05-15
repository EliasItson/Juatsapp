package com.bda.juatsapp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UploadPPController 
{

    @FXML
    Circle currentPP;
    @FXML
    Button uploadPPBtn;
    
    ChatController controller;
    Stage uploadPPStage;
    
    public void initData(ChatController controller, Stage uploadPPStage)
    {
        this.controller = controller;
        this.uploadPPStage = uploadPPStage;
        updateUI();
    }
    
    public void updateUI()
    {
        if(controller.getUserPP() != null)
        {
            Image image = new Image(new ByteArrayInputStream(controller.getUserPP()));
            currentPP.setFill(new ImagePattern(image));
        }
    }
    
    public void uploadPP()
    {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            // Set filters to only allow certain file types, if desired
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show open file dialog
            File imageFile = fileChooser.showOpenDialog(uploadPPStage);
            if (imageFile != null) {
                // Process the selected file (e.g., display it, upload it, etc.)
                controller.uploadPP(imageFile);
                updateUI();
            }
    }
}
