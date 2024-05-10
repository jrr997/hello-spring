package com.jrr997.reggie.service;

import com.jrr997.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-08 11:40:44
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
