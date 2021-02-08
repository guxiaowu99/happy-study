package com.happy.study.reference;

/**
 * @description: 强引用案例
 * @author: zhangmengchao
 * @date: Created in 2021/2/7 下午3:06
 * @version: 1.0
 * @modified By:
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        // 定义强引用
        Object obj1= new Object();
        Object obj2= obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
        /**
         * 运行结果：
         * java.lang.Object@7a7b0070
         * obj2并没有被回收，只要有一个引用还指向这个实例，则垃圾回收器就无法对其进行回收。
         */
    }
}
