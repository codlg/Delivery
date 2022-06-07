package com.delivery.delivery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
