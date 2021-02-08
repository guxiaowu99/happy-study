package com.happy.study.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zhangmengchao
 * @date: Created in 2021/2/8 下午4:51
 * @version: 1.0
 * @modified By:
 */
public class MetaspaceOOMDemo {

    static class OOMTest{
    }
    public static void main(String[] args) {
        int i = 0; // 模拟计数多少次后发生异常
        try {
            while (true){
                i++;
                // Spring 动态代理
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o, args);
                    }
                });
                enhancer.create();
            }
        }catch (Exception e){
            System.out.println("***************多少次后发生了异常:" + i);
            e.printStackTrace();
        }
        /**
         * 运行结果：
         * ***************多少次后发生了异常:1456
         org.springframework.cglib.core.CodeGenerationException: java.lang.OutOfMemoryError-->Metaspace
         at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:538)
         at org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363)
         at org.springframework.cglib.proxy.Enhancer.generate(Enhancer.java:582)
         at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:131)
         at org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319)
         at org.springframework.cglib.proxy.Enhancer.createHelper(Enhancer.java:569)
         at org.springframework.cglib.proxy.Enhancer.create(Enhancer.java:384)
         at com.happy.study.oom.MetaspaceOOMDemo.main(MetaspaceOOMDemo.java:37)
         */
    }
}
