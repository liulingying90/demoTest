package com.example.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ConfigClient implements Watcher {
    private ConfigServer configServer=new ConfigServer();
    private CountDownLatch countDownLatch=new CountDownLatch(1);


    @Override
    public void process(WatchedEvent watchedEvent) {
        String path=watchedEvent.getPath();
        if(watchedEvent.getType()==Event.EventType.NodeChildrenChanged){
            this.updateConfig(path);
        }
    }

    public void updateConfig(String path){
        System.out.print(configServer.readConfig(path.substring(path.lastIndexOf("/")+1),this));
    }

    public void getAllConfig(){
        Map<String,String> map=configServer.getAllConfig();
        Iterator<String> keys=map.keySet().iterator();
        while(keys.hasNext()){
            String key=keys.next();
            String value=map.get(key);
            System.out.println("key:"+key+"value:"+value);
            }
        }

}
