package com.delivery.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;



}
