package com.radebit.springboot.controller;

import com.radebit.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //1、浏览器客户端返回的都是json
    /*
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){

        Map<String,Object> map=new HashMap<>();
        map.put("code","notexist");
        map.put("message",e.getMessage());
        return map;
    }
    */

    //2、自适应错误页面
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){

        Map<String,Object> map=new HashMap<>();
        //传入自己的错误代码 4xx、5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","notexist");
        map.put("message","用户出错了");
        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }
}
