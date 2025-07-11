package com.example.proyectopooparte2.controllers;

import com.example.proyectopooparte2.model.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    @FXML
    private Label labelStats;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Player> jugadores = Player.cargarJugadoresDesdeJSON(new File("stats.json")); // ruta relativa o absoluta

        StringBuilder sb = new StringBuilder();

        for (Player p : jugadores) {
            sb.append("Jugador: ").append(p.getName()).append("\n");
            sb.append("ID: ").append(p.id).append("\n");
            sb.append("Total Respondidas: ").append(p.getTotalAnswered()).append("\n");
            sb.append("Total Correctas: ").append(Player.totalCorrect).append("\n"); // estático
            sb.append("Total Ganadas: ").append(p.getTotalWins()).append("\n");
            sb.append("Total Perdidas: ").append(p.getTotalLoses()).append("\n");
            sb.append("Total Segundos Respondidos: ").append(p.getTotalSecondsAns()).append("\n");
            sb.append("-------------------------------------------------\n");
        }

        labelStats.setText(sb.toString());
        labelStats.setWrapText(true);
    }

    public void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proyectopooparte2/Menu-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Menú");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
