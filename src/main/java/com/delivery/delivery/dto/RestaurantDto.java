package com.delivery.delivery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestaurantDto {
    String name;
    int minOrderPrice;
    int deliveryFee;
}
