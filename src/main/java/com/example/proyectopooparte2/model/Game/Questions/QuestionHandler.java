package com.example.proyectopooparte2.model.Game.Questions;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import static com.example.proyectopooparte2.model.Game.Questions.CategoryColors.createCategoryColors;

public class QuestionHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final File fileQuestions = new File("preguntasJuegoTrivia_final.json");
    public static final List<CategoryColors> colors = createCategoryColors();
    public static List<Question> readQuestionForCategory( CategoryColors targetCategory) {
        List<Question> questionList = new ArrayList<>();

        try {
            if (!fileQuestions.exists()) {
                System.out.println("Archivo no encontrado. Creando archivo...");
                try (Writer writer = new FileWriter(fileQuestions)) {
                    writer.write("{}");
                }
            }

            try (Reader reader = new FileReader(fileQuestions)) {
                Type type = new TypeToken<Map<String, List<Map<String, String>>>>() {}.getType();
                Map<String, List<Map<String, String>>> data = gson.fromJson(reader, type);

                List<Map<String, String>> rawQuestions = data.get(targetCategory.getCategory());

                if (rawQuestions != null) {
                    for (CategoryColors cat : colors) {
                        if (cat.getCategory().equals(targetCategory.getCategory())) {
                            for (Map<String, String> q : rawQuestions) {
                                String pregunta = q.get("pregunta");
                                String respuesta = q.get("respuesta");
                                questionList.add(new Question(cat,pregunta , respuesta));
                            }
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
