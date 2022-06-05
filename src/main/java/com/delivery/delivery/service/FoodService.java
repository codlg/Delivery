package com.delivery.delivery.service;

import com.delivery.delivery.dto.FoodRequestDto;
import com.delivery.delivery.model.Food;
import com.delivery.delivery.model.Restaurant;
import com.delivery.delivery.repository.FoodRepository;
import com.delivery.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerFood(Long restaurantId, List<FoodRequestDto> foodRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant == null){
            throw new IllegalArgumentException("해당 음식점을 찾을 수 없습니다.");
        }

        for(FoodRequestDto foodExist : foodRequestDto){
        int foodPrice = foodExist.getPrice();

        if(foodPrice < 100 || foodPrice > 1000000) {
            throw new IllegalArgumentException("음식가격은 100원이상 1,000,000원미만까지 입력가능합니다.");
        }
        if(foodPrice % 100 != 0) {
            throw new IllegalArgumentException("음식가격은 100원 단위로만 입력 가능합니다.");
        }

        Optional<Food> found = foodRepository.findByRestaurantAndName(restaurant, foodExist.getName());
        if(found.isPresent()){
            throw new IllegalArgumentException("해당 음식은 이미 등록되어있습니다.");
        }

        Food foods = new Food(restaurant, foodExist);
        foodRepository.save(foods);
        }
    }

    @Transactional
    public List<Food> getFoodList(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()-> new NullPointerException("해당하는 음식점이 없습니다.")
        );

        return foodRepository.findAllByRestaurant(restaurant);
    }
}
