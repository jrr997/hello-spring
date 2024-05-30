package com.jrr997.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrr997.reggie.common.Result;
import com.jrr997.reggie.dto.DishDto;
import com.jrr997.reggie.entity.Category;
import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.entity.Employee;
import com.jrr997.reggie.service.CategoryService;
import com.jrr997.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 09:44:31
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("page")
    public Result<Page<DishDto>> page(int page, int pageSize, String name){
        Page<Dish> pageInfo = new Page(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper();
        wrapper.like(name != null, Dish::getName, name).orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, wrapper);

        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDto> dtoRecords = records.stream().map(dish -> {
            DishDto dishDto = new DishDto();
            Category category = categoryService.getById(dish.getCategoryId());
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            BeanUtils.copyProperties(dish, dishDto);
            return dishDto;
        }).collect(Collectors.toList());


        dishDtoPage.setRecords(dtoRecords);
        return Result.success(dishDtoPage);
    }

    @PostMapping()
    public Result<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavors(dishDto);
        return Result.success("新增菜品成功");
    }

    @GetMapping("{id}")
    public Result<DishDto> getById(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavors(id);
        return Result.success(dishDto);
    }

    @PutMapping()
    public Result<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavors(dishDto);
        return Result.success("更新菜品成功");
    }
}
