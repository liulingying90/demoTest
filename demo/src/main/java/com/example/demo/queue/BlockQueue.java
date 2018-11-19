package com.example.demo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class BlockQueue {
    private static final Logger logger = LoggerFactory.getLogger(BlockQueue.class);

    private static final int QUEUE_MAX_SIZE = 1000;

    private BlockingDeque<Long> blockingDeque = new LinkedBlockingDeque<Long>();

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private Set<Long> cacheSet = new HashSet<Long>();

    public BlockQueue() {
    }

    @PostConstruct
     /*@PostConstruct修饰的方法会在服务器加载servlet的时候运行，并且只会被服务器调用一次，被@PostContsruct修饰的方法会在构造
       之后，init（）方法之前运行;*/
    public void startThread() {
        executor.submit(new BlockQueueThread());
    }

    /*
     @PreDestory被@PreDestory修饰的方法会在服务器卸载servlet的时候运行，并且只会被服务器调用一次，被@PreDestory修饰的方法会在
     destory()方法之后运行，在servlet被彻底卸载之前
     */
    @PreDestroy
    public void preDestory() {
        executor.shutdown();
    }

    //往queue添加元素，如果queue已满则放到set里面
    public void push(Long orderId) {
        if (!blockingDeque.offer(orderId)) {
            cacheSet.add(orderId);
        }
    }

    /*
     从quue里面取出元素做业务逻辑处理
     */
    class BlockQueueThread implements Runnable {
        @Override
        public void run() {
             while (true){
                 try{
                     Long orderId=blockingDeque.take();
                     System.out.println("orderId:"+orderId);
                     initBlockingQueue();
                 }catch (Exception e){
                     logger.error(e.getMessage(),e);
                 }


             }
        }
    }

   /*
   向queue添加元素，移除set元素
    */
    private void initBlockingQueue(){
        try {
            Iterator<Long> orderIter = cacheSet.iterator();
            while (orderIter.hasNext()) {
                if (blockingDeque.size() == 1000) {
                    break;
                }else{
                    Long orderId= orderIter.next();
                    blockingDeque.offer(orderId);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
