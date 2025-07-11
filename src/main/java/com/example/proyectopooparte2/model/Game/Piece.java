package com.example.proyectopooparte2.model.Game;

import com.example.proyectopooparte2.model.Board.Box;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private Player player;
    public static Group piece;
    public List<String> answered = new ArrayList<>();
    private boolean winCondition= false;
    private Box box;


    public Piece(Player player) {
        this.player = player;
    }

    public void setPiece(Group piece) {
        Piece.piece = piece;
    }

    public static Group getPiece() {
        return piece;
    }

    public List<String> getAnswered() {
        return answered;
    }

    public Player getPlayer() {
        return player;
    }

    public void movePieceToBox(Box box){
        piece.setLayoutX(box.getBoxview().getLayoutX()-82.5);
        piece.setLayoutY(box.getBoxview().getLayoutY()-82.5);
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void addAnswered(String color) {
        if (!answered.contains(color)) {
            answered.add(color);
        }
    }

    public boolean hasWon() {
        if (answered.size() == 6) {
            winCondition = true;
        }
        return winCondition;
    }
}
