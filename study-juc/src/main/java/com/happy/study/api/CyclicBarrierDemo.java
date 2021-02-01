package com.happy.study.api;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 做加法，与CountDownLatch作用相反
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 上午9:59
 * @version: 1.0
 * @modified By:
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{System.out.println("****开始召唤神龙");});
        for (int i=1; i<= 7; i++){
            final int tempInt = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "\t 收集到第: " + tempInt + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 测试结果
     * 1	 收集到第: 1龙珠
     * 2	 收集到第: 2龙珠
     * 3	 收集到第: 3龙珠
     * 4	 收集到第: 4龙珠
     * 5	 收集到第: 5龙珠
     * 6	 收集到第: 6龙珠
     * 7	 收集到第: 7龙珠
     * ****开始召唤神龙
     */
}
