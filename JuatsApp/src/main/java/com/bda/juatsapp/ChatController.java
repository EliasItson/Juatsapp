package com.bda.juatsapp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
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


/**
 * Controlador para la vista principal de chat.
 */
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
    @FXML
    private ImageView activeChatDeleteIcon;
    
    
    private UsuarioNegocio usuarioNegocio;
    private ChatNegocio chatNegocio;
    private Usuario loggedInUser;
    private ContextMenu contextMenu;
    private Stage popupStage;
    private ObjectId activeChatId;
    private Chat activeChat;
    private MensajeNegocio mensajeNegocio;

    /**
     * Inicializa los datos del usuario y del negocio para el controlador.
     *
     * @param usuario         El usuario que ha iniciado sesión.
     * @param usuarioNegocio  El negocio de usuario utilizado para operaciones de usuario.
     */
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

        chatMsjTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        
    }
    
    /**
     * Inicializa el controlador después de que su raíz de vista haya sido completamente procesada.
     *
     * @param url El localizador de recursos utilizado para encontrar la raíz del objeto.
     * @param rb  El recurso de paquetes utilizado para localizar objetos.
     */
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

        URL imageUrl2 = getClass().getResource("/media/Menu.png");
        if (imageUrl2 != null) {
            Image img = new Image(imageUrl2.toExternalForm());
            userMenu.setImage(img);
        } else {
            System.out.println("Image resource not found.");
        }

    }
    
    /**
     * Carga los mensajes del chat activo en la interfaz gráfica.
     * Si el chat activo contiene mensajes, los ordena por tiempo de envío y los muestra en la ventana de chat.
     */
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
                    if(msj.getTextoMensaje() != null)
                    {
                        if(msj.getEmisorId().equals(loggedInUser.getId()))
                        {
                            Label mensaje = new Label(msj.getTextoMensaje());
                            mensaje.getStyleClass().add("label-right");
                            mensaje.setPrefWidth(Label.USE_COMPUTED_SIZE);
                            mensaje.setPrefHeight(Label.USE_COMPUTED_SIZE);
                            mensaje.setMaxHeight(Label.USE_PREF_SIZE);
                            GridPane.setHalignment(mensaje, HPos.RIGHT);
                            activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
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
                            activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
                            activeChatGrid.addRow(activeChatGrid.getRowCount(), mensaje);
                        }
                    }
                    else if(msj.getImagen() != null)
                    {
                        if(msj.getEmisorId().equals(loggedInUser.getId()))
                        {
                            Image image = new Image(new ByteArrayInputStream(msj.getImagen()));
                            ImageView msjImage = new ImageView(image);
                            msjImage.setFitHeight(250);
                            msjImage.setFitWidth(250);
                            GridPane.setHalignment(msjImage, HPos.RIGHT);
                            activeChatGrid.addRow(activeChatGrid.getRowCount(), msjImage);
                            activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
                        }
                        else
                        {
                            Image image = new Image(new ByteArrayInputStream(msj.getImagen()));
                            ImageView msjImage = new ImageView(image);
                            msjImage.setFitHeight(250);
                            msjImage.setFitWidth(250);
                            GridPane.setHalignment(msjImage, HPos.LEFT);
                            activeChatGrid.addRow(activeChatGrid.getRowCount(), msjImage);
                            activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
                        }
                    }
                }
            }  
        }    
    }
    
    /**
     * Abre la ventana para subir una foto de perfil
     * 
     */
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
            uploadPPController.initData(this, popupStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Abre la ventana para crear un chat nuevo
     * 
     */
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
    /**
     * Le asigna la foto de perfil al usuario
     * 
     * @param imageFile La imagen que selecciono el usuario
     */
    public void uploadPP(File imageFile)
    {
        try
        {
            loggedInUser = usuarioNegocio.uploadPP(imageFile, loggedInUser.getId());
            updateUI();
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Actualiza la interfaz de usuario con los detalles del usuario conectado.
     */
    public void updateUI()
    {
        userDisplayName.setText(loggedInUser.getNombre());
        userCodeLabel.setText("#" + loggedInUser.getCodigo());
            
        if (loggedInUser.getFoto_perfil() != null) 
        {
            Image image = new Image(new ByteArrayInputStream(loggedInUser.getFoto_perfil()));
            userPP.setFill(new ImagePattern(image));
        }
    }
    
    /**
     * Regresa la foto de perfil del usuario
     * 
     * @return la foto de perfil de usuario
     */
    public byte[] getUserPP()
    {
        return loggedInUser.getFoto_perfil();
    }
    
    /**
     * Crea un nuevo chat
     * 
     * @param codigo el codigo de usuario con el cual se va a chatear
     */
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
    
    /**
     * Carga la lista de chats del usuario.
     */
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
                final Usuario activeChatUsuario = usuarioChat;

                Node cell = chatsGridPane.getChildren().stream()
                        .filter(node -> GridPane.getRowIndex(node) == finalRowIndex && GridPane.getColumnIndex(node) == 0)
                        .findFirst().orElse(null);

                if (cell != null) {
                    cell.setOnMouseClicked(event -> {
                        activeChatId = new ObjectId(chatId.getText());
                        setActiveChat(activeChatUsuario);
                        setActiveChatVisible();
                        loadActiveChatMensajes();
                        System.out.println(chatId.getText());
                        System.out.println("yay");
                    });
                }
                
                Mensaje latestMessage = null;
                for (Mensaje msj : chat.getMensajes()) {
                    if (latestMessage == null || msj.getFechaTiempoEnvio().isAfter(latestMessage.getFechaTiempoEnvio())) {
                        latestMessage = msj;
                    }
                }
                String latestMsgText;
                if(latestMessage == null)
                    latestMsgText = "";
                else if(latestMessage.getTextoMensaje() == null)
                    latestMsgText = "Imagen";
                else 
                    latestMsgText = latestMessage.getTextoMensaje();
                    
                
                Label msjChatGridLbl = new Label(latestMsgText);
                msjChatGridLbl.getStyleClass().add("chat-list-label-bottom");
                GridPane.setValignment(msjChatGridLbl, VPos.BOTTOM);
                GridPane.setMargin(msjChatGridLbl, new Insets(0, 0, 10, 100));
                chatsGridPane.add(msjChatGridLbl, 0, rowIndex);

                Circle userChatGridPP = new Circle();
                userChatGridPP.setStrokeWidth(0);
                userChatGridPP.setRadius(35);
                GridPane.setHalignment(userChatGridPP, HPos.LEFT);
                GridPane.setMargin(userChatGridPP, new Insets(0, 0, 0, 13));
                
                if (usuarioChat.getFoto_perfil() != null) 
                {
                    Image image = new Image(new ByteArrayInputStream(usuarioChat.getFoto_perfil()));
                    userChatGridPP.setFill(new ImagePattern(image));
                }
                else
                    userChatGridPP.setFill(Color.web("#e0e0e0"));
                
                chatsGridPane.add(userChatGridPP, 0, rowIndex);
                
                
                rowIndex++;
            }
            
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }    
    }
    
    /**
     * Borra el chat activo
     *
     */
    public void deleteActiveChat()
    {
        openedChatPP.setVisible(false);
        activeChatUserName.setVisible(false);
        activeChatStatus.setVisible(false);
        chatMsjTextField.setVisible(false);
        sendButton.setVisible(false);
        imgButton.setVisible(false);
        activeChatDeleteIcon.setVisible(false);
        try
        {
            chatNegocio.deleteChat(activeChatId);
            chatsGridPane.getChildren().clear();
            loadChatList();
        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Hace visibles a los componentes del chat
     * 
     */
    public void setActiveChatVisible()
    {
        openedChatPP.setVisible(true);
        activeChatUserName.setVisible(true);
        activeChatStatus.setVisible(true);
        chatMsjTextField.setVisible(true);
        sendButton.setVisible(true);
        imgButton.setVisible(true);
        activeChatDeleteIcon.setVisible(true);
    }
    
    /**
     * Determina el chat activo
     * 
     * @param activeChatUsuario El usuario relacionado con el chat activo
     * 
     */
    public void setActiveChat(Usuario activeChatUsuario)
    {
        try
        {
            activeChat = chatNegocio.getChatById(activeChatId);

            if (activeChatUsuario.getFoto_perfil() != null) {
                Image image = new Image(new ByteArrayInputStream(activeChatUsuario.getFoto_perfil()));
                openedChatPP.setFill(new ImagePattern(image));
            } else {
                openedChatPP.setFill(Color.web("#e0e0e0"));
            }

            activeChatUserName.setText(activeChatUsuario.getNombre());

        }
        catch(NegocioException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Manda un mensaje de texo al chat
     * 
     */
    public void sendMessage() 
    {
        if (!chatMsjTextField.getText().isBlank()) 
        {
            Label msj = new Label(chatMsjTextField.getText());
            try
            {
                Mensaje mensaje = mensajeNegocio.guardar(chatMsjTextField.getText(), loggedInUser.getId());
                chatNegocio.updateChat(activeChat, mensaje);
                chatMsjTextField.setText("");
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
            activeChatGrid.addRow(activeChatGrid.getRowCount(), msj);
            activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
        }
    }
    
    /**
     * Manda una imagen al chat
     * 
     */
    public void sendImage()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File imageFile = fileChooser.showOpenDialog(App.getStage());
        if (imageFile != null) 
        {
            try
            {
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                Image image = new Image(new ByteArrayInputStream(imageData));
                Mensaje mensaje = mensajeNegocio.guardar(imageData, loggedInUser.getId());
                chatNegocio.updateChat(activeChat, mensaje);

                ImageView msjImage = new ImageView(image);
                msjImage.setFitHeight(250);
                msjImage.setFitWidth(250);
                GridPane.setHalignment(msjImage, HPos.RIGHT);
                activeChatGrid.addRow(activeChatGrid.getRowCount(), msjImage);
                activeChatScrollPane.setVvalue(activeChatScrollPane.getVmax());
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            } catch (NegocioException e) 
            {
                System.out.println(e.getMessage());
            }
            
        }
    }
}
