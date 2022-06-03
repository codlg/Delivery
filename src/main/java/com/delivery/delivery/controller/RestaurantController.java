package com.delivery.delivery.controller;

import com.delivery.delivery.dto.RestaurantDto;
import com.delivery.delivery.model.Restaurant;
import com.delivery.delivery.repository.RestaurantRepository;
import com.delivery.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    // 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurants(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurants = restaurantService.registerRestaurants(restaurantDto);
        return restaurants;
    }

}
