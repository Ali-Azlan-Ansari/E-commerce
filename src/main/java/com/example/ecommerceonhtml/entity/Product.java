package com.example.ecommerceonhtml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Product {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String image;
 private String name;
 private Double cost;
 private Double price;
 private Double stock;
 private Boolean isActive;


 @ManyToOne
 @JoinColumn(name = "category_id")
 private Category category;

// @ManyToOne
// @JoinColumn(name = "model_id")
// private Model model;


 @JsonIgnore
 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
 private List<ProductCart> productCarts;
}
