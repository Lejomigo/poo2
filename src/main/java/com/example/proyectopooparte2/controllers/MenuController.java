package com.example.proyectopooparte2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class MenuController {

    @FXML Button btnNewGame;

    // Este método debe recibir el evento para obtener el Stage actual
    @FXML
    public void onPlay() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectopooparte2/Tablero-view.fxml"));
            Parent root = loader.load();


            // Obtener el Stage actual a partir del botón o de alguna otra forma
            Stage stage = (Stage) btnNewGame.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Juego Trivia - Tablero");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ No se pudo cargar Tablero-view.fxml");
        }
    }

    public void onStats(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectopooparte2/Stats-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Estadísticas");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onExit() {
        System.exit(0);
    }
}
