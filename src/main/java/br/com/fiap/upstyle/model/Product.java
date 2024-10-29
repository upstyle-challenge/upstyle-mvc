package br.com.fiap.upstyle.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;

    public Product(String name, String description, Double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void edit(Product product) {
        if(product.getName() != null) this.name = product.getName();
        if(product.getDescription() != null) this.description = product.getDescription();
        if(product.getPrice() != null) this.price = product.getPrice();
        if(product.getImageUrl() != null) this.imageUrl = product.getImageUrl();
    }
}
