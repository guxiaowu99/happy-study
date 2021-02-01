package com.happy.study.api;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: 信号量 多用于支付限流。
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 上午10:21
 * @version: 1.0
 * @modified By:
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);  //模拟3个停车位
        // 模拟6辆车
        for (int i=1; i<=6; i++){
            new Thread(() -> {
                try {
                    // 占用
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3s后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放
                    semaphore.release();
                }
                System.out.println();
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 1	 抢到车位
     * 2	 抢到车位
     * 3	 抢到车位
     * 1	 停车3s后离开
     * 2	 停车3s后离开
     * 5	 抢到车位
     * 4	 抢到车位
     * 3	 停车3s后离开
     * 6	 抢到车位
     * 5	 停车3s后离开
     * 4	 停车3s后离开
     * 6	 停车3s后离开
     */
}
