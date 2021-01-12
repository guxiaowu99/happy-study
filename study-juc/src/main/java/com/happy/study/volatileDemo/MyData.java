package com.happy.study.volatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 操作数据
 * @author: zhangmengchao
 * @date: Created in 2021/1/11 下午8:45
 * @version: 1.0
 * @modified By:
 */
public class MyData {

    // 当number没有被volatile修饰时
//    int number = 0;
    // 当number被volatile修饰时
    volatile int number = 0;

    public void addNumberTo60() {
        this.number = 60;
    }

    public void addPlus() {
        number++;
    }

    /**
     * 使用原子类
     */
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
