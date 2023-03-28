package com.example.ecommerceonhtml.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private LocalDateTime dateTime;

//    @JsonIgnore
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<ProductCart> productCarts;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer-id", referencedColumnName = "id")
    private Costumer costumer;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<Transaction> transactions;
}
