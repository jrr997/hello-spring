package com.jrr997.reggie.dto;

import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.entity.DishFlavor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
