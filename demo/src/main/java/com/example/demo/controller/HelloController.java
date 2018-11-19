package com.example.demo.controller;

import com.example.demo.MyAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @MyAnnotation
    @RequestMapping("/add1")
    public String addData(String deviceId){
        return "success";
    }

    @RequestMapping("/add2")
    public void add(){
        System.out.println("aaaa");
    }
}
