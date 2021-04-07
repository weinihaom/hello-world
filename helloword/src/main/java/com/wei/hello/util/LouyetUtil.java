package com.wei.hello.util;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * Created by weiwei on 2021/4/6.
 */
public class LouyetUtil {

    public static void main(String[] args) throws InterruptedException {
        //关闭偏向锁延迟
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        //(00000101 00000000 00000000 00000000) (5)  无锁可偏向
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            //(00000101 01001000 01101110 00000011) (57559045)  偏向锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main2() {
        try {
            //休眠5s，JVM启动了偏向锁
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object o = new Object();
        //(00000101 00000000 00000000 00000000) (5) 无锁可偏向（只有遇到同步块，才会有锁）
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        new Thread(()->{
            synchronized (o){
                //(00000101 10111000 01010110 00011010) (441890821) 偏向锁
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        try {
            //等上一个线程执行结束，此处在模拟让两个线程交替执行
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //(00000101 10111000 01010110 00011010) (441890821) 偏向锁
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(()->{
            synchronized (o){
                //(11100000 11110110 00101110 00011011) (456062688) 轻量锁，存在两个线程交替执行。
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }
}
