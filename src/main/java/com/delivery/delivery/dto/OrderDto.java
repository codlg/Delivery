package com.delivery.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    private List<FoodOrderDto> foods;

    public OrderDto (String restaurantName, int deliveryFee, int totalPrice, List<FoodOrderDto> foods){
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foods = foods;
    }
}
