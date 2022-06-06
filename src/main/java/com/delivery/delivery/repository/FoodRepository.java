package com.delivery.delivery.repository;

import com.delivery.delivery.model.Food;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FoodRepository extends JpaRepository<Food, Long> {

    // 같은 음식점 내에서 음식이름 중복 여부 확인
    Optional<Food> findByRestaurantIdAndName(Long restaurantId, String name);

    // 음식점 한 곳에 대한 메뉴 보여주기
    List<Food> findAllByRestaurantId(Long restaurantId);
}
