package com.delivery.delivery.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    String name;
    int minOrderPrice;
    int deliveryFee;
}
