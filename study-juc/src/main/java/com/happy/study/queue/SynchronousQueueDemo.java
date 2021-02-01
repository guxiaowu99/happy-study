package com.happy.study.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 同步队列 ：不存储元素的阻塞队列,也即单个元素的队列
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 上午11:44
 * @version: 1.0
 * @modified By:
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+ "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+ "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+ "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadB").start();
    }
    /**
     * 结果：产生一个 消费一个
     * ThreadA	 put 1
     * 1
     * ThreadA	 put 2
     * 2
     * ThreadA	 put 3
     * 3
     */
}
