package com.jrr997.reggie.service;

import com.jrr997.reggie.dto.SetmealDto;
import com.jrr997.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 10:14:15
 */
public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    SetmealDto getDetail(Long id);

    void removeByIdWithDish(List<Long> ids);
}
