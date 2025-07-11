package com.example.proyectopooparte2.model.Game.Questions;

public class Question {
    private final CategoryColors category;
    private final String text;
    private final String answer;

    public Question(CategoryColors category, String text, String answer) {
        this.category = category;
        this.text     = text;
        this.answer   = answer;
    }

    public CategoryColors getCategory() { return category; }
    public String   getText()     { return text; }
    public String   getAnswer()   { return answer; }

    /** Comprueba si el texto dado coincide (ignora mayúsculas/minúsculas). */
    public boolean isCorrect(String userInput) {
        return answer.trim().equalsIgnoreCase(userInput.trim());
    }
}
