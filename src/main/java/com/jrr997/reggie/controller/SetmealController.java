package com.jrr997.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrr997.reggie.common.Result;
import com.jrr997.reggie.dto.SetmealDto;
import com.jrr997.reggie.entity.Category;
import com.jrr997.reggie.entity.Setmeal;
import com.jrr997.reggie.service.CategoryService;
import com.jrr997.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 10:14:15
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 分页查询套餐
     */
    @GetMapping("page")
    public Result<Page<SetmealDto>> page(int page, int pageSize, String name){
        Page<Setmeal> pageInfo = new Page(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper();
        wrapper.like(name != null, Setmeal::getName, name).orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, wrapper);

        BeanUtils.copyProperties(pageInfo, setmealDtoPage, "records");

        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> dtoRecords = records.stream().map(setmeal -> {
            SetmealDto setmealDto = new SetmealDto();
            Category category = categoryService.getById(setmeal.getCategoryId());
            if (category != null) {
                setmealDto.setCategoryName(category.getName());
            }
            BeanUtils.copyProperties(setmeal, setmealDto);
            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(dtoRecords);
        return Result.success(setmealDtoPage);
    }

    @PostMapping()
    public Result<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return Result.success("添加成功");
    }

    @GetMapping("{id}")
    public Result<SetmealDto> get(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.getDetail(id);
        return Result.success(setmealDto);
    }

    @DeleteMapping()
    public Result<String> remove(@RequestParam List<Long> ids){
        setmealService.removeByIdWithDish(ids);
        return Result.success("删除成功");
    }
}
