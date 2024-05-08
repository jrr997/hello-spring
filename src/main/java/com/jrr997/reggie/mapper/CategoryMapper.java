package com.jrr997.reggie.mapper;

import com.jrr997.reggie.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author jrr997
 * @since 2024-05-08 11:40:44
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
