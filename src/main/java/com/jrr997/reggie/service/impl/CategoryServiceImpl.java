package com.jrr997.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jrr997.reggie.common.CustomException;
import com.jrr997.reggie.entity.Category;
import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.entity.Setmeal;
import com.jrr997.reggie.mapper.CategoryMapper;
import com.jrr997.reggie.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrr997.reggie.service.DishService;
import com.jrr997.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-08 11:40:44
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private DishService dishService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId, id);
        if (dishService.count(dishQueryWrapper) > 0) {
            throw new CustomException("菜品分类下存在菜品，无法删除");
        }
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId, id);
        if (setmealService.count(setmealQueryWrapper) > 0) {
            throw new CustomException("菜品分类下存在套餐，无法删除");
        }
        removeById(id);
    }
}
