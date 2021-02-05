package com.happy.study;

/**
 * @description: GcRoot案例
 * @author: zhangmengchao
 * @date: Created in 2021/2/5 下午4:53
 * @version: 1.0
 * @modified By:
 * <p>
 * 可作为GC Roots的对象：
 * 1：虚拟机栈(栈帧中的本地变量表)中引用的对象。
 * 2：方法区中的类静态属性引用的对象
 * 3：方法区中常量引用的对象
 * 4：本地方法栈中JNI(即一般说的Native)中引用的对象
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100 * 1024 * 1024];
    // 方法区中静态属性
//    private static GCRootDemo2 t2;
     // 方法区中常量引用的对象
//    private static final GCRootDemo3 t3 = new GCRootDemo3();
    public static void m1() {
        // 方法在栈中 方法中引用了t1
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
