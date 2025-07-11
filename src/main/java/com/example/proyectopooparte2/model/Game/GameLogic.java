package com.example.proyectopooparte2.model.Game;


import com.example.proyectopooparte2.controllers.PreguntaController;
import com.example.proyectopooparte2.controllers.TableroController;
import com.example.proyectopooparte2.model.Board.Box;
import com.example.proyectopooparte2.model.Game.Questions.CategoryColors;
import com.example.proyectopooparte2.model.Game.Questions.Question;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.Consumer;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.proyectopooparte2.model.Game.Questions.CategoryColors.colorearCategorias;
import static com.example.proyectopooparte2.model.Game.Questions.QuestionHandler.colors;
import static com.example.proyectopooparte2.model.Game.Questions.QuestionHandler.readQuestionForCategory;

public class GameLogic {
    private static Object Platform;
    private static final File stats = new File("stats.json");


    public static void actualizar(Piece playerInTurno, TableroController controller){
        controller.setPiece(playerInTurno);
        controller.getSeg().setText(String.valueOf(playerInTurno.getPlayer().getTotalSecondsAns()));
        controller.setNamePlayer(playerInTurno.getPlayer().getName());

        // Actualiza el texto de categorías respondidas
        StringBuilder categorias = new StringBuilder();
        for (String s : playerInTurno.getAnswered()) {
            categorias.append(s).append(".\n");
        }
        controller.getCategorias().setText(categorias.toString());

        // Mueve la única pieza visual a la casilla correspondiente
        playerInTurno.movePieceToBox(playerInTurno.getBox());

        // Colorea la ficha visual según las categorías respondidas
        colorearCategorias(playerInTurno.getAnswered(), controller.getPieceView());
    }


    public static void turn(Piece piece, Box box, TableroController controller, int segundos, Consumer<Boolean> alResponder) {
        controller.setNamePlayer(piece.getPlayer().getName());

        if (piece.hasWon() && box.getId().equals("center")) {
            Random random = new Random();
            Box randomBox = controller.getRaysList().get(random.nextInt(controller.getRaysList().size()));

            answerQuestion(piece, randomBox, controller, segundos, (correcto) -> {
                if (correcto) {
                    controller.mostrarMensajeVictoria(piece.getPlayer());
                }
                piece.getPlayer().guardarDatosComoJSON(piece.getPlayer(), stats);
                alResponder.accept(correcto); // <-- Aquí notificas si acertó o no
            });
            return;
        }

        answerQuestion(piece, box, controller, segundos, (correcto) -> {
            if (correcto) {
                CategoryColors.setColorForWin(box, piece);
                piece.getPlayer().answerPoint();
            }
            piece.getPlayer().guardarDatosComoJSON(piece.getPlayer(), stats);
            alResponder.accept(correcto); // <-- Aquí también
        });
    }



    public static void answerQuestion(Piece piece, Box box, TableroController controller,int segundos,Consumer<Boolean> callback) {
        CategoryColors category = CategoryColors.getCategoryFromBox(box, colors);

        if (category == null) {
            System.out.println("🟥 Casilla roja → No tiene categoría, vuelve a lanzar");
            controller.getDade().setDisable(false);
            callback.accept(true);  // Se notifica como falso porque no hubo pregunta
            return;
        }

        List<Question> questionList = readQuestionForCategory(category);
        if (questionList.isEmpty()) {
            System.err.println("⚠️ No hay preguntas para la categoría: " + category.getCategory());
            callback.accept(false);  // No se pudo formular la pregunta
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(GameLogic.class.getResource("/Pregunta-View.fxml"));
            Parent root = loader.load();

            PreguntaController preguntaController = loader.getController();
            Question questToAnsw = questionList.get(new Random().nextInt(questionList.size()));
            preguntaController.setPregunta(questToAnsw);
            preguntaController.setCategoria(category.getCategory());

            // ✅ Configuramos qué hacer si responde correctamente o incorrectamente
            preguntaController.setOnRespuestaCorrecta(() -> {
                System.out.println("✔ ¡Respuesta correcta!");
                controller.getDade().setDisable(false);
                callback.accept(true);  // Notificamos que fue correcta
            });

            preguntaController.setOnRespuestaIncorrecta(() -> {
                System.out.println("❌ Respuesta incorrecta");
                callback.accept(false);  // Notificamos que fue incorrecta
            });
            preguntaController.iniciarTimer(segundos,piece.getPlayer());
            Stage stage = new Stage();
            stage.setTitle("Responder Pregunta");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            callback.accept(false);  // Falló el proceso
        }
    }

    public static void guardarDatos(List<Piece> pieces){
        for (Piece p: pieces){
            Player.guardarDatosComoJSON(p.getPlayer(),stats);
        }
    }


}