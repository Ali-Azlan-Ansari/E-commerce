package com.example.ecommerceonhtml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart> cart;
}
