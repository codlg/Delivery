package com.delivery.delivery.controller;

import com.delivery.delivery.dto.FoodRequestDto;
import com.delivery.delivery.model.Food;
import com.delivery.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFoods (@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDto){
        foodService.registerFoods(restaurantId, foodRequestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoodList (@PathVariable Long restaurantId){

        return foodService.getFoodList(restaurantId);
    }
}
