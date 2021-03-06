package com.delivery.delivery.model;

import com.delivery.delivery.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Long restaurantId;

    public Food(Long restaurantId, FoodRequestDto foodRequestDto) {
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
