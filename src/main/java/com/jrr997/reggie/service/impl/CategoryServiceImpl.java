package com.jrr997.reggie.service.impl;

import com.jrr997.reggie.entity.Category;
import com.jrr997.reggie.mapper.CategoryMapper;
import com.jrr997.reggie.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
