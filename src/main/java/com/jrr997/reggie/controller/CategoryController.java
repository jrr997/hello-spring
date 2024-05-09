package com.jrr997.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrr997.reggie.common.Result;
import com.jrr997.reggie.entity.Category;
import com.jrr997.reggie.entity.Employee;
import com.jrr997.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author jrr997
 * @since 2024-05-08 11:40:44
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page<Category>> page(int page, int pageSize){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper();
        wrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    }

    @DeleteMapping()
    public Result<String> page(Long id){
        categoryService.removeById(id);
        return Result.success("删除成功");
    }
}
