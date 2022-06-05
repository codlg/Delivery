package com.delivery.delivery.model;

import com.delivery.delivery.dto.FoodRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    private Restaurant restaurant;

    @Builder
    public Food (Restaurant restaurant, FoodRequestDto foodRequestDto){
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
        this.restaurant = restaurant;
    }
}
