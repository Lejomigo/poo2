package com.example.proyectopooparte2.model.Board;

import javafx.scene.shape.Rectangle;

import java.util.*;

public class BoxesGrafo {
    // Usamos Set<Box> para que no haya duplicados
    private final Map<Box, Set<Box>> adyacentList;

    public BoxesGrafo() {
        this.adyacentList = new HashMap<>();
    }

    public void createNodo(Box nodo) {
        adyacentList.putIfAbsent(nodo, new HashSet<>());
    }

    public void addConnection(Box nodo1, Box nodo2) {
        // Aseguramos existencia de los nodos
        createNodo(nodo1);
        createNodo(nodo2);
        // Conexión bidireccional sin duplicados
        adyacentList.get(nodo1).add(nodo2);
        adyacentList.get(nodo2).add(nodo1);
    }

    /**
     * Construye el grafo circular de 36 box y los 6 rayos de 5 casillas cada uno apuntando al centro.
     */
    public void createGrafo(List<Box> boxesList, List<Box> raysList, Box center) {
        // 1) Crear todos los nodos
        boxesList.forEach(this::createNodo);
        raysList .forEach(this::createNodo);
        createNodo(center);

        // 2) Anillo circular de las 36 boxes
        int nBoxes = boxesList.size(); // debe ser 36
        for (int i = 0; i < nBoxes; i++) {
            Box actual  = boxesList.get(i);
            Box siguiente = boxesList.get((i + 1) % nBoxes);
            addConnection(actual, siguiente);
        }

        // 3) Seis rayos de profundidad 5
        int cantidadRayos = 6;
        int profundidad   = 5;
        for (int r = 0; r < cantidadRayos; r++) {
            int baseIndex = r * profundidad;
            // Conectar box de base
            Box boxBase   = boxesList.get(r * 6);       // indices 0,6,12,18,24,30
            Box primerRay = raysList.get(baseIndex);    // indices 0,5,10,15,20,25
            addConnection(boxBase, primerRay);

            // Conectar internamente el rayo
            for (int paso = 0; paso < profundidad - 1; paso++) {
                Box a = raysList.get(baseIndex + paso);
                Box b = raysList.get(baseIndex + paso + 1);
                addConnection(a, b);
            }

            // Último al centro
            Box ultimo = raysList.get(baseIndex + profundidad - 1);
            addConnection(ultimo, center);
        }
    }

    /** Para debug: imprime cada nodo con sus vecinos */
    public void imprimirGrafo() {
        System.out.println("==== GRAFO DE BOXES (Sets) ====");
        adyacentList.forEach((nodo, vecinos) -> {
            System.out.print(nodo.getId() + " -> ");
            vecinos.forEach(v -> System.out.print(v.getId() + " "));
            System.out.println();
        });
    }

    /**
     * Desde una casilla de inicio, devuelve la lista de Rectangles
     * a los que se puede llegar en exactamente 'pasos' movimientos.
     */
    public List<Rectangle> obtenerDestinosConPasos(Box inicio, int pasos) {
        Set<Box> resultados = new HashSet<>();
        Set<Box> camino    = new LinkedHashSet<>();
        camino.add(inicio);
        buscarRecursivo(inicio, pasos, camino, resultados);

        List<Rectangle> rectangulos = new ArrayList<>(resultados.size());
        for (Box b : resultados) {
            rectangulos.add(b.getBoxview());
        }
        return rectangulos;
    }

    /**
     * Auxiliar recursivo con backtracking.
     * - actual: nodo desde donde estoy explorando
     * - pasosRestantes: cuántos saltos faltan
     * - camino: para evitar ciclos
     * - resultados: destinos finales encontrados
     */
    private void buscarRecursivo(Box actual,
                                 int pasosRestantes,
                                 Set<Box> camino,
                                 Set<Box> resultados) {
        if (pasosRestantes == 0) {
            resultados.add(actual);
            return;
        }


        for (Box vecino : adyacentList.getOrDefault(actual, Collections.emptySet())) {
            if (camino.add(vecino)) {
                buscarRecursivo(vecino, pasosRestantes - 1, camino, resultados);
                camino.remove(vecino);  // backtracking
            }
        }
    }



}
