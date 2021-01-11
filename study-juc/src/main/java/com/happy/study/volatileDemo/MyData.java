package com.happy.study.volatileDemo;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/1/11 下午8:45
 * @version: 1.0
 * @modified By:
 */
public class MyData {

    // 当number没有被volatile修饰时
//    int number = 0;
    // 当number没有被volatile修饰时
    volatile int number = 0;

    public void addNumberTo60() {
        this.number = 60;
    }
}
