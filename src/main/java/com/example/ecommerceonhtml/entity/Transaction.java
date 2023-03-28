package com.example.ecommerceonhtml.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Double totalAmount;

//    @ManyToOne
//    @JoinColumn(name = "coupon_id")
//    private Coupon coupon;
//
//    @ManyToOne
//    @JoinColumn(name = "costumer_id")
//    private Costumer costumer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

}
