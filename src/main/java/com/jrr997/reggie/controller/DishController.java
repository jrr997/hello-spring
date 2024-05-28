package com.jrr997.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrr997.reggie.common.Result;
import com.jrr997.reggie.dto.DishDto;
import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.entity.Employee;
import com.jrr997.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("page")
    public Result<Page<Dish>> page(int page, int pageSize){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper();
        wrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    }

    @PostMapping()
    public Result<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavors(dishDto);
        return Result.success("新增菜品成功");
    }
}
