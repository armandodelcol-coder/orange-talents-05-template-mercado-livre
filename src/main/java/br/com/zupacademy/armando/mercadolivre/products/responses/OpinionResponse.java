package br.com.zupacademy.armando.mercadolivre.products.responses;

import br.com.zupacademy.armando.mercadolivre.products.entities.Opinion;

public class OpinionResponse {

    private Integer rating;
    private String title;
    private String description;
    private String userName;

    public OpinionResponse(Opinion opinion) {
        this.rating = opinion.getRating();
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
        this.userName = opinion.getUser().getUsername();
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

}
