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

/**
 * Controlador para la ventana de carga de imagen de perfil.
 */
public class UploadPPController {

    @FXML
    Circle currentPP; // Círculo que muestra la imagen de perfil actual
    @FXML
    Button uploadPPBtn; // Botón para cargar una nueva imagen de perfil

    ChatController controller; // Controlador de la ventana de chat
    Stage uploadPPStage; // Etapa de la ventana de carga de imagen de perfil

    /**
     * Inicializa el controlador con el controlador de la ventana de chat y la etapa de carga de imagen de perfil.
     * @param controller El controlador de la ventana de chat.
     * @param uploadPPStage La etapa de la ventana de carga de imagen de perfil.
     */
    public void initData(ChatController controller, Stage uploadPPStage) {
        this.controller = controller;
        this.uploadPPStage = uploadPPStage;
        updateUI();
    }

    /**
     * Actualiza la interfaz de usuario con la imagen de perfil actual del usuario.
     */
    public void updateUI() {
        if (controller.getUserPP() != null) {
            // Si hay una imagen de perfil, la muestra en el círculo actualPP
            Image image = new Image(new ByteArrayInputStream(controller.getUserPP()));
            currentPP.setFill(new ImagePattern(image));
        }
    }

    /**
     * Método llamado cuando se hace clic en el botón de carga de imagen de perfil.
     * Abre un cuadro de diálogo para seleccionar un archivo de imagen y actualiza la imagen de perfil del usuario.
     */
    public void uploadPP() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        // Filtrar para permitir solo ciertos tipos de archivos de imagen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo para abrir el archivo
        File imageFile = fileChooser.showOpenDialog(uploadPPStage);
        if (imageFile != null) {
            // Procesar el archivo seleccionado (por ejemplo, mostrarlo, cargarlo, etc.)
            controller.uploadPP(imageFile);
            updateUI(); // Actualizar la interfaz de usuario con la nueva imagen de perfil
        }
    }
}
