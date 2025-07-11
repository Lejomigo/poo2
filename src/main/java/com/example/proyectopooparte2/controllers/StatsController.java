package com.example.proyectopooparte2.controllers;

import com.example.proyectopooparte2.model.Game.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.File;
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
}
