package com.platzi.pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int idPizza;
    private double newPrice;
}
