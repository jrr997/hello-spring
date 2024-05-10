package com.jrr997.reggie.mapper;

import com.jrr997.reggie.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 09:44:31
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
