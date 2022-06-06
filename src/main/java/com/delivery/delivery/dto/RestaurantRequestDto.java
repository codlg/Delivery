package com.delivery.delivery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestaurantRequestDto {
    String name;
    int minOrderPrice;
    int deliveryFee;
}
