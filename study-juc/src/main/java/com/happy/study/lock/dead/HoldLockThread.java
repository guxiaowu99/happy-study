package com.happy.study.lock.dead;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/2/3 上午10:17
 * @version: 1.0
 * @modified By:
 */
@Data
public class HoldLockThread implements Runnable{

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockB = lockB;
        this.lockA = lockA;
    }


    @Override
    public void run() {
       synchronized (lockA){
           System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获得: "+ lockB);
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (lockB){
               System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获得: "+ lockA);
           }
       }
    }
}
