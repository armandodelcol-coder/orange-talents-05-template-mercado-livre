package br.com.zupacademy.armando.mercadolivre.products.entities;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Question(String title, User user, Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return this.user.getUsername();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public String getProductOwnerName() {
        return this.product.getOwner().getUsername();
    }

}
