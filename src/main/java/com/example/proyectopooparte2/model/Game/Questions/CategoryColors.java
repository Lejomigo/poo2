package com.example.proyectopooparte2.model.Game.Questions;

import com.example.proyectopooparte2.model.Board.Box;
import com.example.proyectopooparte2.model.Game.Piece;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;


public class CategoryColors {
    String category;
    String color;

    public CategoryColors(String category, String color) {
        setCategory(category);
        setColor(color);

    }

    public static void setColorForWin(Box box, Piece piece){
        String color = convertirColorANombre((Color) box.getBoxview().getFill());
        if (color == null) return;

        Path path = null;
        for (Node node : Piece.getPiece().getChildren()) {
            if (node instanceof Path && color.equals(node.getId())) {
                path = (Path) node;
                break;
            }
        }

        if (path != null) {
            path.setFill(box.getBoxview().getFill());
        }

        piece.addAnswered(color); // << ahora es por jugador, no global
    }

    public static List<CategoryColors> createCategoryColors(){
        List<CategoryColors> categoryColors = new ArrayList<>();
        categoryColors.add(new CategoryColors("Geografía","Azul"));
        categoryColors.add(new CategoryColors("Historia","Amarillo"));
        categoryColors.add(new CategoryColors("Deportes","Naranja"));
        categoryColors.add(new CategoryColors("Ciencia","Verde"));
        categoryColors.add(new CategoryColors("Arte y Literatura", "Morado"));
        categoryColors.add(new CategoryColors("Entretenimiento","Rosado"));
        return categoryColors;
    }

    public static CategoryColors getCategoryFromBox(Box box, List<CategoryColors> categorias) {
        Paint colorBox = box.getColor();
        String nombreDetectado = convertirColorANombre((Color) colorBox);

        if (nombreDetectado == null) {
            return null;
        }

        for (CategoryColors categoria : categorias) {
            if (categoria.getColor().equalsIgnoreCase(nombreDetectado)) {
                return categoria;
            }
        }

        System.err.println("⚠️ No se encontró categoría para: " + nombreDetectado);
        return null;
    }


    private static String convertirColorANombre(Color color) {
        String hex = toHex(color);

        return switch (hex.toLowerCase()) {
            case "#25ff00" -> "Verde";
            case "#ff8800" -> "Naranja";
            case "#e5ff00" -> "Amarillo";
            case "#0f00ff" -> "Azul";
            case "#ff00b7" -> "Rosado";
            case "#7700ff" -> "Morado";
            default -> null;
        };
    }

    public static void colorearCategorias(List<String> categoriasRespondidas, Group piezaVisual) {
        for (Node node : piezaVisual.getChildren()) {
            if (node instanceof Path path) {
                String id = path.getId(); // Ejemplo: "Azul", "Naranja", etc.

                if (id == null) continue;

                if (categoriasRespondidas.contains(id)) {
                    path.setFill(convertirNombreAColor(id));
                } else {
                    path.setFill(Color.WHITE);
                }
            }
        }
    }


    private static Color convertirNombreAColor(String nombre) {
        return switch (nombre) {
            case "Verde" -> Color.web("#25ff00");
            case "Naranja" -> Color.web("#ff8800");
            case "Amarillo" -> Color.web("#e5ff00");
            case "Azul" -> Color.web("#0f00ff");
            case "Rosado" -> Color.web("#ff00b7");
            case "Morado" -> Color.web("#7700ff");
            default -> Color.WHITE;
        };
    }
    private static String toHex(Color color) {
        int r = (int) Math.round(color.getRed() * 255);
        int g = (int) Math.round(color.getGreen() * 255);
        int b = (int) Math.round(color.getBlue() * 255);

        return String.format("#%02x%02x%02x", r, g, b);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setColor(String color) {
        this.color = color;
    }



    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }



    @Override
    public String toString() {
        return "Categoria='" + category + '\'' +
                ", Color='" + color + '\'';
    }
}
