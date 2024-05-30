package com.jrr997.reggie.service;

import com.jrr997.reggie.dto.DishDto;
import com.jrr997.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 09:44:31
 */
public interface DishService extends IService<Dish> {

        public void saveWithFlavors(DishDto dishDto);

    DishDto getByIdWithFlavors(Long id);

    void updateWithFlavors(DishDto dishDto);
}
