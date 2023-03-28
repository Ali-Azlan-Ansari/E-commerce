package com.example.ecommerceonhtml.DTO;

import com.example.ecommerceonhtml.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ModelDTO {
    private Long id;
    private String modelName;
    private Boolean isActive;
    private List<Product> product;
}
