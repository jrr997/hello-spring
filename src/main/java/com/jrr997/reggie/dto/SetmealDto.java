package com.jrr997.reggie.dto;

import com.jrr997.reggie.entity.Setmeal;
import com.jrr997.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private String categoryName;
    private List<SetmealDish> setmealDishes;
}
