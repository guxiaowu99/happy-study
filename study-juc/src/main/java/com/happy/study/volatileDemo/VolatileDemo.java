package com.happy.study.volatileDemo;


import java.util.concurrent.TimeUnit;

/**
 * @description: volatile关键字作用分析测试 1:保证可见性 2:不保证原子性 3:禁止指令重排序
 * @author: zhangmengchao
 * @date: Created in 2021/1/11 下午8:16
 * @version: 1.0
 * @modified By:
 */
public class VolatileDemo {

    public static void main(String[] args) {
        noInsureAtomicity();
    }

    /**
     * volatile不保证原子性案例分析
     */
    public static void noInsureAtomicity() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 观察20个线程执行完成后的最终结果 程序默认有2个线程,GC线程和Main线程。
        while (Thread.activeCount() > 2) {
            // 线程礼让
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t 使用n++最终数据值为:" + myData.number);
        /**
         *  测试结果：
         *  20个线程,每个线程操作数添加10000,但最终数值并不等于20000。
         *  多线程环境下,不能保证数据安全
         *  原因:
         *     number++:底层被拆分成三个指令,没有保证原子性
         *     1:先获取初始值
         *     2:进行+1操作
         *     3:写回主内存
         *   解决方案:
         *     1:方法上添加sync
         *     2:使用juc下的原子整型类  AtomicInteger等原子类中的封装好的方法
         */
        System.out.println(Thread.currentThread().getName() + "\t 使用原子类最终数据值为:" + myData.atomicInteger);
        /**
         *   结果:使用原子类结果为20000,满足原子性。
         */
    }


    /**
     * volatile可以保证可见性,及时通知其他线程,主物理内存的值以及被修改
     */
    public static void seeOkByVolatile() {
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
        /**
         *     测试结果：
         *        1:当number未被volatile修饰时。因为其无法拿到最新值,Main线程一直循环等待。
         *        2:当number被volatile修饰后。当ThreadA线程3S后将数据更新为60时,Main线程获取到最新值60,任务执行结束。
         */
    }
}
