package com.example.demo;
import org.springframework.stereotype.Component;

//@Aspect //切面标识
@Component //组件bean标识
public class PassAspect {
//    @Around("@annotation(pass)")//环绕型切面
//    public Object invoked(ProceedingJoinPoint pjp,Pass pass) throws Throwable {
//        if(pass.value()){
//            pjp.proceed();
//        }
//
//        throw new RuntimeException("不通过");
//    }
}
