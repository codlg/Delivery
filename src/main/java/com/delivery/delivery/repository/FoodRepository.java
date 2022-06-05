package com.delivery.delivery.repository;

import com.delivery.delivery.model.Food;
import com.delivery.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
    Optional<Food> findByRestaurantAndName(Restaurant restaurant, String name);
}
