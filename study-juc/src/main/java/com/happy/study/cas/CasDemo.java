package com.happy.study.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: CAS:比较并交换 => compareAndSet
 * @author: zhangmengchao
 * @date: Created in 2021/1/12 下午2:37
 * @version: 1.0
 * @modified By:
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5); // 主物理内存中的值
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t 当前值为: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t 当前值为: " + atomicInteger.get());
    }
}
