package com.jrr997.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jrr997.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
