package com.example.proyectopooparte2.model.Board;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;



public class Box {
    private final String id;
    private final Rectangle boxview;
    private final Paint color;

    public Box(String id, Rectangle boxview, Paint color) {
        this.id = id;
        this.boxview = boxview;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Rectangle getBoxview() {
        return boxview;
    }

    public Paint getColor() {
        return color;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }



}
