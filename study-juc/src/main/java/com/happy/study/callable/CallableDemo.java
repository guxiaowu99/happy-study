package com.happy.study.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: 多线程 线程的3种创建方式
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 下午4:49
 * @version: 1.0
 * @modified By:
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask, "AAA");
        t1.start();
        // futureTask.get() 建议放到最后 因为会导致main线程阻塞
        while (!futureTask.isDone()){

        }
        System.out.println("*****线程运行结果 " + futureTask.get());
        /**
         * 运行结果:
         * ******* come in callable
         * *****线程运行结果 1024
         */

    }
}
