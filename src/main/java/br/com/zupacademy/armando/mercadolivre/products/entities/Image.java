package br.com.zupacademy.armando.mercadolivre.products.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_product_images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String bucketUrl;

    @NotNull
    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Deprecated
    public Image() {
    }

    public Image(String bucketUrl, Product product) {
        this.bucketUrl = bucketUrl;
        this.product = product;
    }

    public String getBucketUrl() {
        return bucketUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return Objects.equals(bucketUrl, image.bucketUrl) && Objects.equals(product, image.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucketUrl, product);
    }

}
