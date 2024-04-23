package com.jrr997.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jrr997.reggie.common.Result;
import com.jrr997.reggie.entity.Employee;
import com.jrr997.reggie.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(wrapper);

        if (emp == null) {
            return Result.error("登录失败");
        }

        if (!emp.getPassword().equals(password)) {
            return Result.error("登录失败");
        }

        if (emp.getStatus() == 0) {
            return Result.error("账号已被禁用");
        }

        request.getSession().setAttribute("employee", emp.getId());

        return Result.success(emp);
    }

    @PostMapping("/logout")
    public Result<Employee> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return Result.success(null);
    }

    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return Result.success("添加成功");
    }

    @GetMapping("/page")
    public Result<Page<Employee>>page(int page, int pageSize, String name){
        log.info("分页查询");
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper();
        wrapper.like(StringUtils.isNotEmpty(name),Employee::getName, name);
        wrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    }

    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody  Employee employee) {
        Long id = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(id);
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);

        return Result.success("员工信息修改成功");
    }

    @GetMapping("/{id}")
    public Result<Employee> get(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            return Result.error("员工不存在");
        }
        return Result.success(employee);
    }
}
