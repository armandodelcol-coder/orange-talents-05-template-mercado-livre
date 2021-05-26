package br.com.zupacademy.armando.mercadolivre.products.requests;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.products.entities.Question;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;

import javax.validation.constraints.NotBlank;

public class NewQuestionRequest {

    @NotBlank
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public Question toModel(Product product, User user) {
        return new Question(this.title, user, product);
    }

}
