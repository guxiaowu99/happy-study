package com.happy.study.collectionDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: 集合类线程不安全问题
 * @author: zhangmengchao
 * @date: Created in 2021/1/13 上午10:24
 * @version: 1.0
 * @modified By:
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
//       List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i=1; i<=3 ;i++){
           new Thread(() ->{
               list.add(UUID.randomUUID().toString().substring(0,8));
               System.out.println(list);
           },String.valueOf(i)).start();
       }
       // 当线程变多的时候 会出现 java.util.ConcurrentModificationException
        /**
         * 故障现象：
         *   会出现 java.util.ConcurrentModificationException
         *
         * 导致原因：
         *   并发修改导致，一个线程正在写，另一个过来抢，导致并发修改异常。
         *
         * 解决方案：
         * 1: new Vector();
         * 2: Collections.synchronizedList(new ArrayList<>());
         * 3: juc包里的类 new CopyOnWriteArrayList<>();
         *
         * 源码分析：
         * 1:获取锁
         * 2:得到原数组
         * 3:新建一个比原数组长度+1的新数组，并拷贝原数组到新数组
         * 4:将要添加的元素赋值给新数组的最后一个空间
         * 5:最后新数组赋值给volatile修饰的array数组
         *
         */
    }
}
