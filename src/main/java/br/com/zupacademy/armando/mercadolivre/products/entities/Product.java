package br.com.zupacademy.armando.mercadolivre.products.entities;

import br.com.zupacademy.armando.mercadolivre.categories.entities.Category;
import br.com.zupacademy.armando.mercadolivre.products.requests.AttributeRequest;
import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull @Positive
    @Column(columnDefinition = "decimal", nullable = false, scale = 10, precision = 2)
    private BigDecimal price;

    @NotNull @PositiveOrZero
    @Column(columnDefinition = "smallint unsigned", nullable = false)
    private Integer stock;

    @NotBlank @Length(max = 1000)
    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @NotNull
    @ManyToOne @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull @Size(min = 3)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Attribute> attributes = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public Product(String name,
                   BigDecimal price,
                   Integer stock,
                   String description,
                   Category category,
                   List<AttributeRequest> attributesRequest,
                   User owner) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.attributes = attributesRequest.stream().map(attr -> attr.toModel(this)).collect(Collectors.toSet());
        this.owner = owner;
    }

}
