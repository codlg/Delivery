package com.delivery.delivery.service;


import com.delivery.delivery.dto.FoodOrderDto;
import com.delivery.delivery.dto.FoodOrderRequestDto;
import com.delivery.delivery.dto.OrderDto;
import com.delivery.delivery.dto.OrderRequestDto;
import com.delivery.delivery.model.Food;
import com.delivery.delivery.model.FoodOrder;
import com.delivery.delivery.model.Order;
import com.delivery.delivery.model.Restaurant;
import com.delivery.delivery.repository.FoodOrderRepository;
import com.delivery.delivery.repository.FoodRepository;
import com.delivery.delivery.repository.OrdersRepository;
import com.delivery.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final OrdersRepository ordersRepository;

    public OrderDto registerOrder(OrderRequestDto orderRequestDto) {

        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 음식점이 없습니다.")
        );

        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = 0;

        // DB에 저장
        List<FoodOrder> foodOrderList = new ArrayList<>();

        // 요청에 대한 응답
        List<FoodOrderDto> foods = new ArrayList<>();

        for(FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()){
            Food food = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    ()-> new IllegalArgumentException("음식 정보가 존재하지 않습니다.")
            );

            int quantity = foodOrderRequestDto.getQuantity();

            if(quantity < 1 || quantity > 100 ) {
                throw new IllegalArgumentException("주문 수량은 1 ~ 100 까지만 가능합니다.");
            }

            FoodOrder foodOrder = new FoodOrder(food, quantity);

            totalPrice += foodOrder.getPrice();

            foodOrderList.add(foodOrder);

            foodOrderRepository.save(foodOrder);

            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, foodOrder.getPrice());

            foods.add(foodOrderDto);

        }

        if(totalPrice < restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("최소 주문 금액을 넘지 않았습니다.");
        }

        totalPrice += deliveryFee;

        Order order = new Order(restaurantName, deliveryFee, totalPrice, foodOrderList);

        OrderDto orderDto = new OrderDto(restaurantName, deliveryFee, totalPrice, foods);


        ordersRepository.save(order);


        return orderDto;

    }

    public List<Order> getOrder() {
        return ordersRepository.findAll();
    }
}
