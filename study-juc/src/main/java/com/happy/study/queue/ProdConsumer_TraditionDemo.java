package com.happy.study.queue;

/**
 * @description: 消费者生产者 传统版
 * @author: zhangmengchao
 * @date: Created in 2021/2/1 下午2:43
 * @version: 1.0
 * @modified By:
 * <p>
 * 题目：一个初始值为零的变量，两个线程对其进行交替操作，一个加一，一个减一，来5轮。
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();
        new Thread(() ->{
            for (int i= 1; i<= 5; i++){
                try {
                    shareDate.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(() ->{
            for (int i= 1; i<= 5; i++){
                try {
                    shareDate.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ThreadB").start();
    }
    /**
     * 运行结果
     * ThreadA	 1
     * ThreadB	 0
     * ThreadA	 1
     * ThreadB	 0
     * ThreadA	 1
     * ThreadB	 0
     * ThreadA	 1
     * ThreadB	 0
     * ThreadA	 1
     * ThreadB	 0
     */
}
