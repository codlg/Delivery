package com.delivery.delivery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "Order_tb")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany
    @JoinColumn(name = "FOOD_ID")
    private List<FoodOrder> foods;

    public Order(String restaurantName, int deliveryFee, int totalPrice, List<FoodOrder> foodOrderList ){
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foods = foodOrderList;
    }
}
