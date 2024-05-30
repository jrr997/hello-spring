package com.jrr997.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jrr997.reggie.dto.DishDto;
import com.jrr997.reggie.entity.Dish;
import com.jrr997.reggie.entity.DishFlavor;
import com.jrr997.reggie.mapper.DishMapper;
import com.jrr997.reggie.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrr997.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private DishFlavorService dishFlavorService;


    /**
     * 新增菜品，同时保存口味
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavors(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishId);
            }
            dishFlavorService.saveBatch(flavors);
        }
    }


    public DishDto getByIdWithFlavors(Long id) {
        DishDto dishDto = new DishDto();
        Dish dish = this.getById(id);
        BeanUtils.copyProperties(dish, dishDto);

        List<DishFlavor> flavors = dishFlavorService.list(new QueryWrapper<DishFlavor>().eq("dish_id", id));
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Transactional
    public void updateWithFlavors(DishDto dishDto) {
        this.updateById(dishDto);

        Long dishId = dishDto.getId();
        dishFlavorService.remove(new QueryWrapper<DishFlavor>().eq("dish_id", dishId));

        List<DishFlavor> flavors = dishDto.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishId);
            }
            dishFlavorService.saveBatch(flavors);
        }
    }
}
