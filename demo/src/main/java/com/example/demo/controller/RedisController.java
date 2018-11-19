package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private static final Logger logger=LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;


    @RequestMapping("/setStr")
    public String setStr(String key,String val){
        try{
            redisService.setStr(key,val);
            return "success";
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return "error";
        }

    }

    @RequestMapping("/getStr")
    public String getStr(String key){
        return redisService.getStr(key);
    }

    @RequestMapping("/delStr")
    public String delStr(String key){
        try{
            redisService.del(key);
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return "error";
        }

    }

    @RequestMapping("/setObj")
    public String setObject(String key,User user){
        try{
            redisService.setObj(key,user);
            return "success";
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return "error";
        }
    }

    @RequestMapping("/getObj")
    public Object getObj(String key){
        return redisService.getObj(key);
    }

    @RequestMapping("/delObject")
    public String delObj(String key){
        try{
            redisService.delObj(key);
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return "error";
        }
    }
}
