package com.happy.study.volatileDemo;

/**
 * @description: 单例模式
 * @author: zhangmengchao
 * @date: Created in 2021/1/12 上午11:12
 * @version: 1.0
 * @modified By:
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo");
    }

//    public static SingletonDemo getInstance() {
//        if (instance == null) {
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    /**
     * synchronized模式 保证并发单例模式安全  有点重 不推荐
     * @return
     */
//    public static synchronized SingletonDemo getInstance() {
//        if (instance == null) {
//            instance = new SingletonDemo();
//        }
//        return instance;
//    }

    /**
     * DCL模式(Double check Lock 双端检锁机制) 加锁前 加锁后都判断
     * 双端检锁机制并不一定保证线程安全,因为可能存在指令重排,因此需要加入volatile禁止指令重排序
     * @return
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程 Main
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        /**
         * 结果：
         * main	 构造方法SingletonDemo
         * true
         * true
         * true
         */
        System.out.println("------------------------------------------------");
        // 并发多线程
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }

        /**
         * 结果
         * 1	 构造方法SingletonDemo
         * 2	 构造方法SingletonDemo
         * 解决方案:
         *      1:添加synchronized
         */
    }
}
