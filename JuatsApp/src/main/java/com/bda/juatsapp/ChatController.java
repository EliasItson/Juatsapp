package com.bda.juatsapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import modelo.Usuario;
import negocio.NegocioException;
import negocio.UsuarioNegocio;
import org.bson.types.ObjectId;

public class ChatController implements Initializable {

    @FXML
    private Circle userPP;
    @FXML
    private Label userDisplayName;
    @FXML
    private ImageView userMenu;
    @FXML
    private Circle openedChatPP;
    @FXML
    private ImageView sendButton;
    @FXML
    private ImageView imgButton;
    @FXML
    private TextField chatMsjTextField;
    @FXML
    private GridPane activeChatGrid;
    @FXML
    private ScrollPane activeChatScrollPane;
    @FXML
    private Label userCodeLabel;
    
    private ObjectId loggedInUserId;
    private UsuarioNegocio usuarioNegocio;
    private Usuario loggedInUser;

    public void initData(Usuario usuario)
    {
        this.loggedInUser = usuario;
        this.usuarioNegocio = new UsuarioNegocio();
        updateUI();
    }
    
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


        URL imageUrl2 = getClass().getResource("/media/Menu.png");
        if (imageUrl2 != null) {
            Image img = new Image(imageUrl2.toExternalForm());
            userMenu.setImage(img);
        } else {
            System.out.println("Image resource not found.");
        }

    }
    
    public void updateUI()
    {
        userDisplayName.setText(loggedInUser.getNombre());
        userCodeLabel.setText("#" + loggedInUser.getCodigo());
    }

    public void sendMessage() {
        if (!chatMsjTextField.getText().isBlank()) 
        {
            Label msj = new Label(chatMsjTextField.getText());
            msj.getStyleClass().add("label-right");
            msj.setPrefWidth(Label.USE_COMPUTED_SIZE);
            msj.setPrefHeight(Label.USE_COMPUTED_SIZE);
            msj.setMaxHeight(Label.USE_PREF_SIZE);
            GridPane.setHalignment(msj, HPos.RIGHT);
            activeChatScrollPane.setVvalue(1);
            activeChatGrid.addRow(activeChatGrid.getRowCount(), msj);
        }
    }
}
