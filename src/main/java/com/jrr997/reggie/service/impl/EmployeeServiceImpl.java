package com.jrr997.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jrr997.reggie.entity.Employee;
import com.jrr997.reggie.mapper.EmployeeMapper;
import com.jrr997.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
