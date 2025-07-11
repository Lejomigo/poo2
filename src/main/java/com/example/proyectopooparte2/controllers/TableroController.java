package com.example.proyectopooparte2.controllers;

import com.example.proyectopooparte2.model.Board.Box;
import com.example.proyectopooparte2.model.Board.BoxesGrafo;
import com.example.proyectopooparte2.model.Game.GameLogic;
import com.example.proyectopooparte2.model.Game.Piece;
import com.example.proyectopooparte2.model.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableroController {
    @FXML private Rectangle box1, box2, box3, box4, box5, box6,
            box7, box8, box9, box10, box11, box12,
            box13, box14, box15, box16, box17, box18,
            box19, box20, box21, box22, box23, box24,
            box25, box26, box27, box28, box29, box30,
            box31, box32, box33, box34, box35, box36;
    @FXML private Rectangle ray1, ray2, ray3, ray4, ray5,
            ray6, ray7, ray8, ray9, ray10,
            ray11, ray12, ray13, ray14, ray15,
            ray16, ray17, ray18, ray19, ray20,
            ray21, ray22, ray23, ray24, ray25,
            ray26, ray27, ray28, ray29, ray30;
    @FXML private Rectangle center;
    @FXML private Group pieceView;
    @FXML private Label dadeNum, namePlayer,seg,categorias;
    @FXML private Button dade;

    //variables normales
    private int seconds;
    private List<Box> boxesList;
    private List<Box> raysList;
    private Box centerBox;
    private BoxesGrafo grafo;

    private Piece piece;
    private Box inicio;
    private  int turn = 0;
    private List<Piece> pieceList = new ArrayList<>();


    private Box selectedBox;

    // Selecciona una casilla de inicio (por ejemplo box1)


    @FXML
    public void initialize(){
        centerBox = new Box(center.getId(), center,center.getFill());
        pedirSegundos();
        centerBox.getBoxview().setUserData(centerBox);
        centerBox.getBoxview().setOnMouseClicked(event -> {
            Box clickedBox = (Box) ((Rectangle) event.getSource()).getUserData();
            System.out.println("Clicked box id: " + clickedBox.getId());

            // Deshabilitar la casilla para evitar más clicks
            clickedBox.getBoxview().setDisable(true);

            // Aquí puedes agregar más lógica para cuando se haga click en el centro
        });

        Piece piece1 = new Piece(new Player("1","player1"));
        Piece piece2 = new Piece(new Player("2","player2"));

        pieceList = List.of(piece1,piece2);
        inicio=centerBox;
        for (Piece p : pieceList) {
            p.setPiece(pieceView);
            p.setBox(centerBox);
            p.movePieceToBox(centerBox);
        }
        piece = pieceList.get(turn);
        GameLogic.actualizar(piece,this);

        boxesList = List.of(
                new Box(box1.getId(), box1, box1.getFill()),
                new Box(box2.getId(), box2, box2.getFill()),
                new Box(box3.getId(), box3, box3.getFill()),
                new Box(box4.getId(), box4, box4.getFill()),
                new Box(box5.getId(), box5, box5.getFill()),
                new Box(box6.getId(), box6, box6.getFill()),
                new Box(box7.getId(), box7, box7.getFill()),
                new Box(box8.getId(), box8, box8.getFill()),
                new Box(box9.getId(), box9, box9.getFill()),
                new Box(box10.getId(), box10, box10.getFill()),
                new Box(box11.getId(), box11, box11.getFill()),
                new Box(box12.getId(), box12, box12.getFill()),
                new Box(box13.getId(), box13, box13.getFill()),
                new Box(box14.getId(), box14, box14.getFill()),
                new Box(box15.getId(), box15, box15.getFill()),
                new Box(box16.getId(), box16, box16.getFill()),
                new Box(box17.getId(), box17, box17.getFill()),
                new Box(box18.getId(), box18, box18.getFill()),
                new Box(box19.getId(), box19, box19.getFill()),
                new Box(box20.getId(), box20, box20.getFill()),
                new Box(box21.getId(), box21, box21.getFill()),
                new Box(box22.getId(), box22, box22.getFill()),
                new Box(box23.getId(), box23, box23.getFill()),
                new Box(box24.getId(), box24, box24.getFill()),
                new Box(box25.getId(), box25, box25.getFill()),
                new Box(box26.getId(), box26, box26.getFill()),
                new Box(box27.getId(), box27, box27.getFill()),
                new Box(box28.getId(), box28, box28.getFill()),
                new Box(box29.getId(), box29, box29.getFill()),
                new Box(box30.getId(), box30, box30.getFill()),
                new Box(box31.getId(), box31, box31.getFill()),
                new Box(box32.getId(), box32, box32.getFill()),
                new Box(box33.getId(), box33, box33.getFill()),
                new Box(box34.getId(), box34, box34.getFill()),
                new Box(box35.getId(), box35, box35.getFill()),
                new Box(box36.getId(), box36, box36.getFill())


        );
        for (Box b: boxesList){
            Rectangle view = b.getBoxview();
            view.setUserData(b);

            view.setOnMouseClicked(event -> {
                Box clickedBox = (Box) ((Rectangle) event.getSource()).getUserData();
            });
            view.setDisable(true);

        }
        raysList =List.of(
                new Box(ray1.getId(), ray1, ray1.getFill()),
                new Box(ray2.getId(), ray2, ray2.getFill()),
                new Box(ray3.getId(), ray3, ray3.getFill()),
                new Box(ray4.getId(), ray4, ray4.getFill()),
                new Box(ray5.getId(), ray5, ray5.getFill()),
                new Box(ray6.getId(), ray6, ray6.getFill()),
                new Box(ray7.getId(), ray7, ray7.getFill()),
                new Box(ray8.getId(), ray8, ray8.getFill()),
                new Box(ray9.getId(), ray9, ray9.getFill()),
                new Box(ray10.getId(), ray10, ray10.getFill()),
                new Box(ray11.getId(), ray11, ray11.getFill()),
                new Box(ray12.getId(), ray12, ray12.getFill()),
                new Box(ray13.getId(), ray13, ray13.getFill()),
                new Box(ray14.getId(), ray14, ray14.getFill()),
                new Box(ray15.getId(), ray15, ray15.getFill()),
                new Box(ray16.getId(), ray16, ray16.getFill()),
                new Box(ray17.getId(), ray17, ray17.getFill()),
                new Box(ray18.getId(), ray18, ray18.getFill()),
                new Box(ray19.getId(), ray19, ray19.getFill()),
                new Box(ray20.getId(), ray20, ray20.getFill()),
                new Box(ray21.getId(), ray21, ray21.getFill()),
                new Box(ray22.getId(), ray22, ray22.getFill()),
                new Box(ray23.getId(), ray23, ray23.getFill()),
                new Box(ray24.getId(), ray24, ray24.getFill()),
                new Box(ray25.getId(), ray25, ray25.getFill()),
                new Box(ray26.getId(), ray26, ray26.getFill()),
                new Box(ray27.getId(), ray27, ray27.getFill()),
                new Box(ray28.getId(), ray28, ray28.getFill()),
                new Box(ray29.getId(), ray29, ray29.getFill()),
                new Box(ray30.getId(), ray30, ray30.getFill())
        );
        for (Box r: raysList){
            Rectangle view = r.getBoxview();
            view.setUserData(r);

            view.setOnMouseClicked(event -> {
                Box clickedBox = (Box) ((Rectangle) event.getSource()).getUserData();
            });
            view.setDisable(true);
        }
        piece.setPiece(pieceView);
        grafo = new BoxesGrafo();

        centerBox.getBoxview().setUserData(centerBox);
        grafo.createGrafo(boxesList,raysList,centerBox);


    }
    public void onDade(){
        dade.setDisable(true);
        if (grafo == null || centerBox == null) {
            System.err.println("ERROR: grafo o centerBox no están inicializados.");
            return;
        }
        int min = 1, max = 6;
        int aleatorio = min + (int)(Math.random() * (max - min + 1));

        dadeNum.setText("Sacaste: " + aleatorio);

        List<Rectangle> posiblesDestinos = grafo.obtenerDestinosConPasos(piece.getBox(), aleatorio);
        System.out.println(posiblesDestinos.size());
        for (Rectangle r: posiblesDestinos){
            System.out.println("x");
            r.setStroke(Color.GREEN);
            r.setStrokeWidth(5);
            r.setDisable(false);
            r.setOnMouseClicked(event -> {
                Rectangle clicked = (Rectangle) event.getSource();
                Box clickedBox = (Box) clicked.getUserData();

                selectedBox = clickedBox;

                piece.setBox(selectedBox);


                for (Rectangle b : posiblesDestinos) {
                    b.setDisable(true);
                    b.setStroke(Color.BLACK);
                    b.setStrokeWidth(1);
                    b.setOnMouseClicked(null);
                }
                piece.movePieceToBox(selectedBox);
                GameLogic.turn(piece, selectedBox, this, seconds, (correcto) -> {
                    if (correcto) {
                        System.out.println("¡Respondió bien!");
                        piece = pieceList.get(turn);

                    } else {
                        turn = (turn + 1) % pieceList.size();
                        piece = pieceList.get(turn);
                        dade.setDisable(false);
                    }
                    GameLogic.actualizar(piece,this);
                });
                dadeNum.setText("");
            });
            GameLogic.actualizar(piece,this);
        }

    }

    public Button getDade() {
        return dade;
    }

    public Label getNamePlayer(){
        return namePlayer;
    }

    public void setNamePlayer(String text) {
        namePlayer.setText(text);
        System.out.println("hola");
    }

    public Label getSeg() {
        return seg;
    }

    public void onExit(){
        System.exit(0);
    }

    public Label getCategorias() {
        return categorias;
    }

    public Piece getPiece() {
        return piece;
    }

    public Group getPieceView() {
        return pieceView;
    }

    public List<Box> getRaysList() {
        return raysList;
    }

    public void pedirSegundos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Seconds-view.fxml"));
            Parent root = loader.load();

            SecondsController controller = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal
            stage.setTitle("Ingrese segundos");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Espera a que se cierre

            seconds = controller.getValorIngresado();

            if (seconds > 0) {
                System.out.println("El valor ingresado fue: " + seconds);
                // Usar ese valor en lo que quieras
            } else {
                System.out.println("No se ingresó un valor válido");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarMensajeVictoria(Player ganador) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Victoria!");
        alert.setHeaderText(null);
        alert.setContentText("🎉 El jugador " + ganador.getName() + " ha ganado la partida 🎉");
        alert.showAndWait();
        ganador.setTotalWins(ganador.getTotalWins()+1);

        for (Piece p: pieceList){
            if (p.getPlayer() != ganador ){
                p.getPlayer().setTotalLoses(p.getPlayer().getTotalLoses());
            }
        }

    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setPieceView(Group pieceView) {
        this.pieceView = pieceView;
    }

    @FXML
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