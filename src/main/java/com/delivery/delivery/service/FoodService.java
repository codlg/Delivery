package com.delivery.delivery.service;

import com.delivery.delivery.dto.FoodRequestDto;
import com.delivery.delivery.model.Food;
import com.delivery.delivery.model.Restaurant;
import com.delivery.delivery.repository.FoodRepository;
import com.delivery.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public void registerFoods(Long restaurantId, List<FoodRequestDto> requestDto) {

        for(FoodRequestDto foodRequestDto : requestDto){

            int price = foodRequestDto.getPrice();

            // 음식 가격 허용 범위
            if(price < 100 || price > 1000000) {
                throw new IllegalArgumentException("음식가격은 100원이상 1,000,000원미만까지 입력가능합니다.");
            }

            if(price % 100 != 0) {
                throw new IllegalArgumentException("음식가격은 100원 단위로만 입력 가능합니다.");
            }

            // 음식명 중복
            Optional<Food> foodExist = foodRepository.findByRestaurantIdAndName(restaurantId, foodRequestDto.getName());

            if(foodExist.isPresent()) {
                throw new IllegalArgumentException("해당 음식은 이미 등록되어있습니다.");
            }

            Food food = new Food(restaurantId, foodRequestDto);
            foodRepository.save(food);
        }
    }

    public List<Food> getFoodList(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
