package com.example.proyectopooparte2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondsController {

    @FXML
    private TextField secField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button irBtn;

    private int valorIngresado = -1;  // Por defecto inválido

    @FXML
    public void initialize() {
        errorLabel.setVisible(false);

        irBtn.setOnAction(event -> {
            String input = secField.getText().trim();

            if (esEnteroPositivo(input)) {
                valorIngresado = Integer.parseInt(input);
                ((Stage) irBtn.getScene().getWindow()).close(); // Cierra la ventana
            } else {
                errorLabel.setText("Debe ser un entero positivo");
                errorLabel.setVisible(true);
            }
        });
    }

    private boolean esEnteroPositivo(String input) {
        try {
            int valor = Integer.parseInt(input);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getValorIngresado() {
        return valorIngresado;
    }
}
