package com.radebit.springboot.controller;

import com.radebit.springboot.dao.DepartmentDao;
import com.radebit.springboot.dao.EmployeeDao;
import com.radebit.springboot.entities.Department;
import com.radebit.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工并返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        //调用getAll方法，查出所有员工
        Collection<Employee> employee = employeeDao.getAll();
        //放到请求域中
        model.addAttribute("emps",employee);
        return "emp/list";
    }

    //来到员工添加页
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //取出部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //来到添加页面
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定
    //要求：请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到员工列表页
        //System.out.println("保存的员工信息"+employee);
        //保存员工信息
        employeeDao.save(employee);
        //redirect：重定向
        //forward：转发
        // '/'代表当前项目路径
        return "redirect:/emps";
    }

    //来到修改页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //取出部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到add页面（add 添加修改二合一）
        return "emp/add";
    }

    //员工修改
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        //System.out.println("员工修改信息："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
