package com.happy.study.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 原子引用
 * @author: zhangmengchao
 * @date: Created in 2021/1/12 下午5:29
 * @version: 1.0
 * @modified By:
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        // 原子引用包装类
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User userA = new User("A",22);
        User userB = new User("B",25);
        atomicReference.set(userA);
        System.out.println(atomicReference.compareAndSet(userA,userB)+"\t  最终结果"+ atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(userA,userB)+"\t  最终结果"+ atomicReference.get().toString());
        /**
         * 结果:
         * true	  最终结果User(userName=B, age=25)
         * false	  最终结果User(userName=B, age=25)
         */

    }
}
