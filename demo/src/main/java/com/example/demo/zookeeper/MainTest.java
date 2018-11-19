package com.example.demo.zookeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

public class MainTest {
    public static void main(String[] args){

/*        CountDownLatch countDownLatch=new CountDownLatch(1);
        ConfigClient configClient=new ConfigClient();
        configClient.updateConfig("jdbc.url");
        try{
            countDownLatch.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }*/
    }

    @Test
    public void addConfig(){
        ConfigServer configServer=new ConfigServer();
        configServer.createRoot();
        configServer.write("jdbc.url","com.mysql.jsbc.Driver");
        configServer.write("jdbc.password","1234");
        configServer.close();
    }

    @Test
    public void updateConfig(){
        ConfigServer configServer=new ConfigServer();
        configServer.write("jdbc.url","com.mysql.jdbc.update.test2");
        configServer.close();
    }

}
