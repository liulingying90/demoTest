package com.example.demo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigServer extends ConnectWatcher{
    private String rootPath="/config";

    public ConfigServer() {
        this.connect();
    }

    //写入配置信息
    public void write(String key,String value){
        try{
            Stat stat=this.zookeeper.exists(rootPath+"/"+key,false);
            if(stat!=null){
                this.zookeeper.setData(rootPath+"/"+key,value.getBytes(),-1);
            }else{
                this.zookeeper.create(rootPath+"/"+key,value.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        }catch(KeeperException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public Map<String,String> getAllConfig(){
        Map<String,String> resultMap=new HashMap<String,String>();
        try{
            List<String> paths=this.zookeeper.getChildren(rootPath,false);
            for(String path:paths){
                System.out.println(path);
                String value=new String(this.zookeeper.getData(rootPath+"/"+path,false,null));
                resultMap.put(path,value);
            }
        }catch (KeeperException e){
            e.printStackTrace();
        }catch (InterruptedException e){

        }
        return resultMap;
    }

    public void createRoot(){
        try{
            this.zookeeper.create("/config",null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }catch(KeeperException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public String readConfig(String key, Watcher watcher){
        byte[] bytes=null;
        try{
            bytes=this.zookeeper.getData(rootPath+"/"+key,watcher,null);
        }catch(KeeperException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return new String(bytes);
    }


}
