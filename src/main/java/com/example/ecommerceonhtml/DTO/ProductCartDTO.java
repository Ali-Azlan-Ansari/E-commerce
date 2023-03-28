package com.example.ecommerceonhtml.DTO;

import com.example.ecommerceonhtml.entity.Cart;
import com.example.ecommerceonhtml.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ProductCartDTO {
    private Long id;
    private Float Quantity;
    private Double price;
    private Double cost;
    private Product product;
    private Cart cart;
}
