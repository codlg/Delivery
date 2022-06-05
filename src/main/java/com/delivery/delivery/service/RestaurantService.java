package com.delivery.delivery.service;

import com.delivery.delivery.dto.RestaurantDto;
import com.delivery.delivery.model.Restaurant;
import com.delivery.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant registerRestaurants (RestaurantDto restaurantDto){

        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        if(minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new IllegalArgumentException("최소주문 가격은 1,000원 이상 100,000원미만까지 주문 가능합니다.");
        }
        if(minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("주문 가경은 100원 단위로만 입력이 가능합니다.");
        }

        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException("기본 배달비는 0원 이상 1,0000원미만까지 가능합니다.");
        }
        if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("배달비는 500원 단위로만 입력이 가능합니다.");
        }

        Restaurant restaurants = new Restaurant(restaurantDto);
        return restaurantRepository.save(restaurants);
    }


}
