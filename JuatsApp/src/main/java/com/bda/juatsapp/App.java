package com.bda.juatsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase principal que inicia la aplicación JavaFX.
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    /**
     * Inicia la aplicación JavaFX.
     *
     * @param stage El escenario principal para esta aplicación, en el cual se puede establecer la escena de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML("login"), 1480, 900);
        scene.getStylesheets().add(getClass().getResource("/styles/ParentStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Establece el archivo FXML raíz para la escena.
     *
     * @param fxml El nombre del archivo FXML que se va a cargar.
     * @return El objeto FXMLLoader utilizado para cargar el archivo FXML.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    static FXMLLoader setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
        return fxmlLoader;
    }

    /**
     * Carga el archivo FXML especificado.
     *
     * @param fxml El nombre del archivo FXML que se va a cargar.
     * @return El nodo raíz del archivo FXML cargado.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Devuelve el escenario principal de la aplicación.
     *
     * @return El escenario principal de la aplicación.
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * El método principal que inicia la aplicación.
     *
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }

}
