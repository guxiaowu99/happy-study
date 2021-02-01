package com.happy.study.api;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/1/18 下午8:51
 * @version: 1.0
 * @modified By:
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

//        for (int i=1; i<= 6; i++){
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName()+ "\t 处理完毕");
//            },String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName()+ "\t 最后收尾");
        /**
         * 1	 处理完毕
         * main	 最后收尾
         * 4	 处理完毕
         * 2	 处理完毕
         * 3	 处理完毕
         * 6	 处理完毕
         * 5	 处理完毕
         *
         * 运行结果可以看出。线程处理出现了混乱
         */

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=1; i<= 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ "\t 处理完毕");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+ "\t 最后收尾");
    }
    /**
     * 1	 处理完毕
     * 2	 处理完毕
     * 3	 处理完毕
     * 4	 处理完毕
     * 5	 处理完毕
     * 6	 处理完毕
     * main	 最后收尾
     *
     * 执行顺序得到控制。
     */
}
