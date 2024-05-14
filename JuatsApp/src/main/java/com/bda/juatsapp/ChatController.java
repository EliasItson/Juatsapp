package com.bda.juatsapp;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Chat;
import modelo.Mensaje;
import modelo.Usuario;
import negocio.ChatNegocio;
import negocio.MensajeNegocio;
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
    private Label activeChatUserName;
    @FXML
    private Label activeChatStatus;
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
    @FXML
    private GridPane chatsGridPane;
    
    
    private UsuarioNegocio usuarioNegocio;
    private ChatNegocio chatNegocio;
    private Usuario loggedInUser;
    private ContextMenu contextMenu;
    private Stage popupStage;
    private ObjectId activeChatId;
    private Chat activeChat;
    private MensajeNegocio mensajeNegocio;

    public void initData(Usuario usuario, UsuarioNegocio usuarioNegocio)
    {
        this.loggedInUser = usuario;
        this.usuarioNegocio = usuarioNegocio;
        this.chatNegocio = new ChatNegocio();
        this.mensajeNegocio = new MensajeNegocio();
        updateUI();
        
        loadChatList();
        
        chatsGridPane.getChildren().forEach(node -> {
            node.setOnMouseEntered(event -> {
                node.getStyleClass().add("highlighted-cell");
            });

            node.setOnMouseExited(event -> {
                node.getStyleClass().remove("highlighted-cell");
            });
        });
        
//        chatsGridPane.setOnMouseClicked(event -> {
//            if (event.getTarget() instanceof Node) {
//                Node clickedNode = (Node) event.getTarget();
//                System.out.println("Clicked target: " + clickedNode);
//            }
//        });
        
//        chatsGridPane.getChildren().forEach(node -> {
//    node.setOnMouseClicked(event -> {
//        // Check if the component is a GridPane containing the desired label
//        if (node instanceof GridPane) {
//            GridPane cell = (GridPane) node;
//            for (Node component : cell.getChildren()) {
//                // Check if the component is a label with the specified ID or CSS class
//                if (component instanceof Label && component.getId().equals("chatId")) {
//                    Label activeChatLabel = (Label) component;
//
//                    // Extract the text value of the specific label
//                    String activeChatIdText = activeChatLabel.getText();
//
//                    // Do something with the text value
//                    this.activeChatId = new ObjectId(activeChatIdText);
//                    System.out.println(this.activeChatId.toString());
//                    break;
//                }
//            }
//        }
//    });
//});
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        contextMenu = new ContextMenu();
        contextMenu.getStyleClass().add("context-menu");
        MenuItem item1 = new MenuItem("Subir imagen de perfil");
        MenuItem item2 = new MenuItem("Crear chat nuevo");
        contextMenu.getItems().addAll(item1, item2);
        item1.setOnAction((ActionEvent e) -> openSubirImagen());
        item2.setOnAction((ActionEvent e) -> openCrearChat());
        
        userMenu.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(userMenu, event.getScreenX(), event.getScreenY());
            }
        });
        
        

        URL imageUrl = getClass().getResource("/media/car.png");
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
    
    private void loadActiveChatMensajes()
    {
        activeChatGrid.getChildren().clear();
        if(activeChat.getMensajes() != null)
        {
            if(!activeChat.getMensajes().isEmpty())
            {
                List<Mensaje> sortedMsj = activeChat.getMensajes();
                Collections.sort(sortedMsj, Comparator.comparing(Mensaje::getFechaTiempoEnvio));
                for(Mensaje msj : sortedMsj)
                {
                    if(msj.getEmisorId().equals(loggedInUser.getId()))
                    {
                        Label mensaje = new Label(msj.getTextoMensaje());
                        mensaje.getStyleClass().add("label-right");
                        mensaje.setPrefWidth(Label.USE_COMPUTED_SIZE);
                        mensaje.setPrefHeight(Label.USE_COMPUTED_SIZE);
                        mensaje.setMaxHeight(Label.USE_PREF_SIZE);
                        GridPane.setHalignment(mensaje, HPos.RIGHT);
                        activeChatScrollPane.setVvalue(1);
                        activeChatGrid.addRow(activeChatGrid.getRowCount(), mensaje);
                    }
                    else
                    {
                        Label mensaje = new Label(msj.getTextoMensaje());
                        mensaje.getStyleClass().add("label-left");
                        mensaje.setPrefWidth(Label.USE_COMPUTED_SIZE);
                        mensaje.setPrefHeight(Label.USE_COMPUTED_SIZE);
                        mensaje.setMaxHeight(Label.USE_PREF_SIZE);
                        GridPane.setHalignment(mensaje, HPos.LEFT);
                        activeChatScrollPane.setVvalue(1);
                        activeChatGrid.addRow(activeChatGrid.getRowCount(), mensaje);
                    }
                }
            }  
        }    
    }
    
    private void openSubirImagen() {
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Imagen de Perfil");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("uploadpp.fxml"));
            Parent root = loader.load();
            Scene popupScene = new Scene(root, 594, 306);
            popupStage.setScene(popupScene);
            popupStage.show();
            UploadPPController uploadPPController = loader.getController();
            uploadPPController.initData(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void openCrearChat() {
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Chat Nuevo");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearchat.fxml"));
            Parent root = loader.load();
            Scene popupScene = new Scene(root, 594, 306);
            popupStage.setScene(popupScene);
            popupStage.show();
            CrearChatController crearChatController = loader.getController();
            crearChatController.initData(this, popupStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateUI()
    {
        userDisplayName.setText(loggedInUser.getNombre());
        userCodeLabel.setText("#" + loggedInUser.getCodigo());
    }
    
    public void createChat(String codigo)
    {
        try
        {
            chatNegocio.createChat(loggedInUser, codigo);
            chatsGridPane.getChildren().clear();
            loadChatList();
            
            
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void loadChatList()
    {
        try
        {
            List<Chat> chats = new ArrayList<>();
            chats = chatNegocio.getChatsByUsuario(loggedInUser.getId());
            
            for(Chat chat : chats)
            {
                Usuario usuarioChat = new Usuario();
                for(Usuario usuario : chat.getMiembros())
                {
                    if(!usuario.getId().equals(loggedInUser.getId()))
                    {
                        usuarioChat = usuario;
                        break;
                    }
                }
                

                
                int rowIndex = chatsGridPane.getRowCount();
        
                chatsGridPane.addRow(rowIndex);

                Label nameChatGridLbl = new Label(usuarioChat.getNombre());
                nameChatGridLbl.getStyleClass().add("chat-list-label-top");
                GridPane.setValignment(nameChatGridLbl, VPos.TOP);
                GridPane.setMargin(nameChatGridLbl, new Insets(10, 0, 0, 100));
                chatsGridPane.add(nameChatGridLbl, 0, rowIndex);

                Label chatId = new Label(chat.getId().toString());
                chatId.setId("chatId");
                chatId.setVisible(false);
                chatsGridPane.add(chatId, 0, rowIndex);
                

                final int finalRowIndex = rowIndex;

                Node cell = chatsGridPane.getChildren().stream()
                        .filter(node -> GridPane.getRowIndex(node) == finalRowIndex && GridPane.getColumnIndex(node) == 0)
                        .findFirst().orElse(null);

                if (cell != null) {
                    cell.setOnMouseClicked(event -> {
                        activeChatId = new ObjectId(chatId.getText());
                        setActiveChat();
                        setActiveChatVisible();
                        loadActiveChatMensajes();
                        System.out.println(chatId.getText());
                        System.out.println("yay");
                    });
                }
                
                Label msjChatGridLbl = new Label("...");
                msjChatGridLbl.getStyleClass().add("chat-list-label-bottom");
                GridPane.setValignment(msjChatGridLbl, VPos.BOTTOM);
                GridPane.setMargin(msjChatGridLbl, new Insets(0, 0, 10, 100));
                chatsGridPane.add(msjChatGridLbl, 0, rowIndex);

                Circle userChatGridPP = new Circle();
                userChatGridPP.setStrokeWidth(0);
                userChatGridPP.setRadius(35);
                GridPane.setHalignment(userChatGridPP, HPos.LEFT);
                GridPane.setMargin(userChatGridPP, new Insets(0, 0, 0, 13));
                URL circleIconUrl = getClass().getResource("/media/car.png");
                if (circleIconUrl != null) 
                {
                    Image img = new Image(circleIconUrl.toExternalForm());
                    userChatGridPP.setFill(new ImagePattern(img));
                } 
                else 
                    System.out.println("Image resource not found.");
                chatsGridPane.add(userChatGridPP, 0, rowIndex);
                
                

                ImageView trashIconChatGrid = new ImageView();
                trashIconChatGrid.setFitHeight(24);
                trashIconChatGrid.setFitWidth(24);
                GridPane.setHalignment(trashIconChatGrid, HPos.RIGHT);
                GridPane.setMargin(trashIconChatGrid, new Insets(0, 10, 0, 0));
                URL trashIconUrl = getClass().getResource("/media/icon_trash.png");
                if (trashIconUrl != null) 
                {
                    Image img = new Image(trashIconUrl.toExternalForm());
                    trashIconChatGrid.setImage(img);
                } 
                else 
                    System.out.println("Image resource not found.");
                
                chatsGridPane.add(trashIconChatGrid, 0, rowIndex);
                rowIndex++;
            }
            
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }    
    }
    
    public void setActiveChatVisible()
    {
        openedChatPP.setVisible(true);
        activeChatUserName.setVisible(true);
        activeChatStatus.setVisible(true);
        chatMsjTextField.setVisible(true);
        sendButton.setVisible(true);
        imgButton.setVisible(true);
    }
    
    public void setActiveChat()
    {
        try
        {
            activeChat = chatNegocio.getChatById(activeChatId);
            URL circleIconUrl = getClass().getResource("/media/car.png");
            if (circleIconUrl != null) 
            {
                Image img = new Image(circleIconUrl.toExternalForm());
                openedChatPP.setFill(new ImagePattern(img));
            } 
            else 
                System.out.println("Image resource not found.");
            
            for(Usuario usuario : activeChat.getMiembros())
            {
                if(!usuario.getId().equals(loggedInUser.getId()))
                {
                    activeChatUserName.setText(usuario.getNombre());
                    break;
                }
            }
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage() 
    {
        if (!chatMsjTextField.getText().isBlank()) 
        {
            Label msj = new Label(chatMsjTextField.getText());
            try
            {
                Mensaje mensaje = mensajeNegocio.guardar(chatMsjTextField.getText(), loggedInUser.getId());
                chatNegocio.updateChat(activeChat, mensaje);
            }
            catch(NegocioException e)
            {
                System.out.println(e.getMessage());
            }
            
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
