package com.jrr997.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrr997.reggie.common.CustomException;
import com.jrr997.reggie.dto.SetmealDto;
import com.jrr997.reggie.entity.Setmeal;
import com.jrr997.reggie.entity.SetmealDish;
import com.jrr997.reggie.mapper.SetmealMapper;
import com.jrr997.reggie.service.SetmealDishService;
import com.jrr997.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 10:14:15
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 保存套餐及套餐菜品
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        // 保存套餐
        this.save(setmealDto);

        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishes();

        // 保存套餐菜品
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setSetmealId(setmealDto.getId().toString());
        }
        setmealDishService.saveBatch(setmealDishList);
    }

    public SetmealDto getDetail(Long id) {
        SetmealDto setmealDto = new SetmealDto();
        Setmeal setmeal = this.getById(id);
        BeanUtils.copyProperties(setmeal, setmealDto);
        QueryWrapper<SetmealDish> wrapper = new QueryWrapper<SetmealDish>().eq("setmeal_id", id);
        List<SetmealDish> setmealDishes = setmealDishService.list(wrapper);
        setmealDto.setSetmealDishes(setmealDishes);
        return setmealDto;
    }

    @Transactional
    public  void removeByIdWithDish(List<Long> ids) {
        // 停售的不能删除
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<Setmeal>().in(Setmeal::getId, ids).eq(Setmeal::getStatus, 1);
        long count = this.count(wrapper);
        if (count > 0) {
            throw new CustomException("停售的套餐不能删除");
        }

        for (Long id : ids) {
            setmealDishService.remove(new QueryWrapper<SetmealDish>().eq("setmeal_id", id));
        }
        this.removeByIds(ids);
    }
}
