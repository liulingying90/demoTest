package com.example.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 功能创建ZK的连接及关闭
 */
public class ConnectWatcher implements Watcher {

    private final static String ZK_HOST="127.0.0.1:2181";
    private final static int SESSION_TIME_OUT=3000;
    private CountDownLatch countDownLatch=new CountDownLatch(1);
    ZooKeeper zookeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
         if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
             countDownLatch.countDown();
         }
    }

    public void connect(){
        try{
            zookeeper=new ZooKeeper(ZK_HOST,SESSION_TIME_OUT,this);
            countDownLatch.await();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            this.zookeeper.close();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
