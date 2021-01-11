package com.happy.study.volatileDemo;


import java.util.concurrent.TimeUnit;

/**
 * @description: volatile关键字测试----验证volatile可见性
 * @author: zhangmengchao
 * @date: Created in 2021/1/11 下午8:16
 * @version: 1.0
 * @modified By:
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 进入操作数据");
            // 暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将number改为60
            myData.addNumberTo60();
            System.out.println(Thread.currentThread().getName() + "\t 更新数据值为:" + myData.number);
        }, "ThreadA").start();

        // 第二个线程Main线程 因为线程A需要等待3秒，查看当number被volatile修饰后Main线程执行变化，观察Main线程执时间
        while (myData.number == 0) {
            // 等待 循环
        }
        System.out.println(Thread.currentThread().getName() + "\t 任务完成,得到数据值为:" + myData.number);
    }

    /**
     *     测试结果：
     *        1:当number未被volatile修饰时。因为其无法拿到最新值,Main线程一直循环等待。
     *        2:当number被volatile修饰后。当ThreadA线程3S后将数据更新为60时,Main线程获取到最新值60,任务执行结束。
     */
}
