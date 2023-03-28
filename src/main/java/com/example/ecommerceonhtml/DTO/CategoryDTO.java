package com.example.ecommerceonhtml.DTO;

import com.example.ecommerceonhtml.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private Boolean isActive;
    private List<Product> product;
}
