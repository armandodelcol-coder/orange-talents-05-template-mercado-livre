package br.com.zupacademy.armando.mercadolivre.products.responses;

import br.com.zupacademy.armando.mercadolivre.products.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailsResponse {

    private String name;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private LocalDateTime createdAt;
    private String categoryName;
    private String ownerEmail;
    private List<AttributeResponse> attributes;
    private List<String> images;
    private List<OpinionResponse> opinions;
    private List<QuestionResponse> questions;
    private Integer totalOpinions;
    private BigDecimal averageOpinions;

    public ProductDetailsResponse(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
        this.createdAt = product.getCreatedAt();
        this.categoryName = product.getCategory().getName();
        this.ownerEmail = product.getOwner().getUsername();
        this.attributes = product.getAttributes().stream().map(attribute -> new AttributeResponse(attribute)).collect(Collectors.toList());
        this.images = product.getImages().stream().map(image -> image.getBucketUrl()).collect(Collectors.toList());
        this.opinions = product.getOpinions().stream().map(opinion -> new OpinionResponse(opinion)).collect(Collectors.toList());
        this.questions = product.getQuestions().stream().map(question -> new QuestionResponse(question)).collect(Collectors.toList());
        this.totalOpinions = product.getTotalOpinions();
        this.averageOpinions = product.getAverageOpinions();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public List<AttributeResponse> getAttributes() {
        return attributes;
    }

    public List<String> getImages() {
        return images;
    }

    public List<OpinionResponse> getOpinions() {
        return opinions;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public Integer getTotalOpinions() {
        return totalOpinions;
    }

    public BigDecimal getAverageOpinions() {
        return averageOpinions;
    }

}
