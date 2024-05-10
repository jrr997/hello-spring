package com.jrr997.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.mapper.DishMapper;
import com.jrr997.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrr997.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 09:44:31
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

}
