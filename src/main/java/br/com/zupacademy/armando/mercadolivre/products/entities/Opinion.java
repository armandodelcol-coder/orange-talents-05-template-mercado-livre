package br.com.zupacademy.armando.mercadolivre.products.entities;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_product_opinion")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Min(1) @Max(5)
    @Column(columnDefinition = "tinyint unsigned", nullable = false)
    private Integer rating;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank @Length(max = 500)
    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Deprecated
    public Opinion() {
    }

    public Opinion(Integer rating, String title, String description, Product product, User user) {
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.product = product;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opinion)) return false;
        Opinion opinion = (Opinion) o;
        return Objects.equals(rating, opinion.rating) && Objects.equals(title, opinion.title) && Objects.equals(description, opinion.description) && Objects.equals(product, opinion.product) && Objects.equals(user, opinion.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, title, description, product, user);
    }

}
