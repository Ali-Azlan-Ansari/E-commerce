package com.example.ecommerceonhtml.DTO;

import com.example.ecommerceonhtml.entity.Category;
//import com.example.ecommerceonhtml.entity.Model;
import com.example.ecommerceonhtml.entity.ProductCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String image;
    private String name;
    private Double cost;
    private Double price;
    private Double stock;
    private Boolean isActive;



    private Category category;

//    private Model model;

    private List<ProductCart> productCarts;
}
