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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String couponCode;
    private Double discount;
    private Float Percentage;
    private Boolean isActive;
//    @JsonIgnore
//    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Transaction> orderOfCustomers;



    @JsonIgnore
    @OneToMany(mappedBy = "coupon",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart> cart;
}
