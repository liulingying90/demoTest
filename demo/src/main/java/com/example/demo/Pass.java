package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})//作用范围 方法
@Retention(RetentionPolicy.RUNTIME) //生命周期 代码运行时
public @interface Pass {
    boolean value() default false;
}
