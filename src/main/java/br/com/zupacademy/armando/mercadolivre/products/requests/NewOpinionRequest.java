package br.com.zupacademy.armando.mercadolivre.products.requests;

import br.com.zupacademy.armando.mercadolivre.products.entities.Opinion;
import br.com.zupacademy.armando.mercadolivre.products.entities.Product;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class NewOpinionRequest {

    @NotNull @Min(1) @Max(5)
    private Integer rating;
    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String description;

    public NewOpinionRequest(Integer rating, String title, String description) {
        this.rating = rating;
        this.title = title;
        this.description = description;
    }

    public Opinion toModel(Product product, User user) {
        return new Opinion(
                this.rating,
                this.title,
                this.description,
                product,
                user
        );
    }

}
