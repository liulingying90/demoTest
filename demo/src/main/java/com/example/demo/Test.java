package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Test {
    @Pass(true)
    void foo(){}

    @Pass()
    void foo1(){}
}
