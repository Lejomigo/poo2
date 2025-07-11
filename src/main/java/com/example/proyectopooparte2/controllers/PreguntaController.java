package com.example.proyectopooparte2.controllers;

import com.example.proyectopooparte2.model.Game.Player;
import com.example.proyectopooparte2.model.Game.Questions.Question;
import com.example.proyectopooparte2.model.Game.Questions.CompareAnswers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class PreguntaController {

    @FXML
    private Label labelPregunta;
    @FXML
    private Label labelCategoria, labelSegundos;

    @FXML
    private TextField inputRespuesta;

    @FXML
    private Button botonResponder;



    private Timeline timeline;
    private int segundosRestantes;

    private Question preguntaActual;

    private Runnable onRespuestaCorrecta;

    private Runnable onRespuestaIncorrecta;

    public void setOnRespuestaIncorrecta(Runnable onRespuestaIncorrecta) {
        this.onRespuestaIncorrecta = onRespuestaIncorrecta;
    }

    public void setPregunta(Question pregunta) {
        this.preguntaActual = pregunta;
        labelPregunta.setText(pregunta.getText());
    }

    public void setCategoria(String categoria) {
        labelCategoria.setText(categoria);
    }

    public void setOnRespuestaCorrecta(Runnable callback) {
        this.onRespuestaCorrecta = callback;
    }

    @FXML
    public void initialize() {
        botonResponder.setOnAction(e -> {
            if (preguntaActual == null) return;

            if (timeline != null) timeline.stop();

            String respuestaUsuario = inputRespuesta.getText().trim();
            String respuestaCorrecta = preguntaActual.getAnswer();

            boolean esCorrecta = CompareAnswers.compareAnswers(respuestaCorrecta, respuestaUsuario);
            Stage stage = (Stage) botonResponder.getScene().getWindow();

            if (esCorrecta) {
                if (onRespuestaCorrecta != null) {
                    onRespuestaCorrecta.run();
                }
            } else {
                if (onRespuestaIncorrecta != null) {
                    onRespuestaIncorrecta.run();
                }
            }

            stage.close();
        });
    }
    public void iniciarTimer(int segundos, Player player){
        segundosRestantes = segundos;
        labelSegundos.setText(segundosRestantes + "s");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            segundosRestantes--;
            labelSegundos.setText(segundosRestantes + "s");
            player.setTotalSecondsAns(player.getTotalSecondsAns() + 1);

            if (segundosRestantes <= 0) {
                timeline.stop();

                // Simula una respuesta incorrecta
                if (onRespuestaIncorrecta != null) {
                    onRespuestaIncorrecta.run();
                }

                // Cierra la ventana
                Platform.runLater(() -> {
                    Stage stage = (Stage) botonResponder.getScene().getWindow();
                    stage.close();
                });
            }
        }));
        timeline.setCycleCount(segundos);
        timeline.play();
    }}
