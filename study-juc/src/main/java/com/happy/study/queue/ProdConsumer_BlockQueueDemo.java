package com.happy.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 高并发版本消费者生产者
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 下午3:43
 * @version: 1.0
 * @modified By:
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5s后 main线程终止执行");
        myResource.stop();
    }

    /**
     * 运行结果:
     * Prod	 生产线程启动
     * Consumer	 消费线程启动
     * Prod	 插入队列1成功
     * Consumer	 消费1成功
     * Prod	 插入队列2成功
     * Consumer	 消费2成功
     * Prod	 插入队列3成功
     * Consumer	 消费3成功
     * Prod	 插入队列4成功
     * Consumer	 消费4成功
     * Prod	 插入队列5成功
     * Consumer	 消费5成功
     * 5s后 main线程终止执行
     * Prod	 生产动作结束
     * Consumer	 超时2秒没有取到值,消费退出
     */
}
