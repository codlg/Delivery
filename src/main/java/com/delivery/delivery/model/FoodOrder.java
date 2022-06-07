package com.delivery.delivery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FoodOrder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Builder
    public FoodOrder(Food food, int quantity){
        this.name = food.getName();
        this.price = food.getPrice() * quantity;
        this.quantity = quantity;
    }
}
