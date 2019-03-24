package com.radebit.springboot.controller;

import com.radebit.springboot.dao.EmployeeDao;
import com.radebit.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    //查询所有员工并返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        //调用getAll方法，查出所有员工
        Collection<Employee> employee = employeeDao.getAll();
        //放到请求域中
        model.addAttribute("emps",employee);
        return "emp/list";
    }
}
