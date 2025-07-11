package com.example.proyectopooparte2.model.Game;

import com.example.proyectopooparte2.model.Game.Questions.Question;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

public class Player {
    public final String id;
    public final String name;
    private int totalAnswered = 0;
    public static int totalCorrect = 0;
    private int totalWins = 0;
    private int totalLoses = 0;
    private int totalSecondsAns = 0;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTotalAnswered() {
        return totalAnswered;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public void setTotalLoses(int totalLoses) {
        this.totalLoses = totalLoses;
    }

    public int getTotalLoses() {
        return totalLoses;
    }



    public static void answerPoint(){
        totalCorrect++;
    }

    public int getTotalSecondsAns() {
        return totalSecondsAns;
    }

    public static void guardarDatosComoJSON(Player nuevoJugador, File archivo) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)  // Ignorar 'position'
                .create();

        List<Player> jugadores = new ArrayList<>();

        // 1. Cargar jugadores existentes
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                JsonElement json = JsonParser.parseReader(reader);
                Type listType = new TypeToken<List<Player>>() {}.getType();

                if (json.isJsonArray()) {
                    jugadores = gson.fromJson(json, listType);
                } else if (json.isJsonObject()) {
                    Player unico = gson.fromJson(json, Player.class);
                    jugadores.add(unico);
                }
            } catch (IOException | JsonSyntaxException e) {
                System.out.println("⚠️ Error al leer JSON previo. Se creará nuevo.");
            }
        }

        // 2. Buscar jugador por ID y sumar atributos si existe
        boolean actualizado = false;
        for (int i = 0; i < jugadores.size(); i++) {
            Player actual = jugadores.get(i);
            if (actual.getId().equals(nuevoJugador.getId()) || actual.getName().equalsIgnoreCase(nuevoJugador.getName())) {
                // Sumar atributos numéricos
                actual.totalAnswered += nuevoJugador.totalAnswered;
                actual.totalWins += nuevoJugador.totalWins;
                actual.totalLoses += nuevoJugador.totalLoses;
                actual.totalSecondsAns += nuevoJugador.totalSecondsAns;
                // Nota: totalCorrect es estático, no lo sumamos aquí

                jugadores.set(i, actual);
                actualizado = true;
                break;
            }
        }

        // 3. Si no se encontró, agregar nuevo jugador
        if (!actualizado) {
            jugadores.add(nuevoJugador);
        }

        // 4. Guardar la lista actualizada
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(jugadores, writer);
            System.out.println("✅ Jugador " + (actualizado ? "actualizado" : "agregado") + ": " + nuevoJugador.getName());
        } catch (IOException e) {
            System.out.println("❌ Error al guardar JSON:");
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setTotalSecondsAns(int i) {
        this.totalSecondsAns = i;
    }

    public static List<Player> cargarJugadoresDesdeJSON(File archivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT).create();
        List<Player> jugadores = new ArrayList<>();

        try (FileReader reader = new FileReader(archivo)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            Type listType = new TypeToken<List<Player>>() {}.getType();

            if (jsonElement.isJsonArray()) {
                jugadores = gson.fromJson(jsonElement, listType);
            } else if (jsonElement.isJsonObject()) {
                Player jugador = gson.fromJson(jsonElement, Player.class);
                jugadores.add(jugador);
            }

            System.out.println("✅ Jugadores cargados: " + jugadores.size());
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("❌ Error al leer JSON:");
            e.printStackTrace();
        }

        return jugadores;
    }
}
