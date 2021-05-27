package br.com.zupacademy.armando.mercadolivre.products.responses;

import br.com.zupacademy.armando.mercadolivre.products.entities.Question;

import java.time.LocalDateTime;

public class QuestionResponse {

    private String title;
    private LocalDateTime date;
    private String userName;

    public QuestionResponse(Question question) {
        this.title = question.getTitle();
        this.date = question.getCreatedAt();
        this.userName = question.getUserName();
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

}
