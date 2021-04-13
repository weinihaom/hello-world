package com.wei.hello.util;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.nio.IntBuffer;

/**
 * Created by weiwei on 2021/4/8.
 * @author weiwei
 */
public class ThreadPoolExecutorUtil {
    private int corePoolSize = Runtime.getRuntime().availableProcessors();
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
    public ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize * 2 - 1, 60L,
            TimeUnit.SECONDS, queue, Executors.defaultThreadFactory());


    public static void main(String[] args) {
        // 分配新的int缓冲区，参数为缓冲区容量
        // 新缓冲区的当前位置将为零，其界限(限制位置)将为其容量。它将具有一个底层实现数组，其数组偏移量将为零。
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); ++i) {
            int j = 2 * (i + 1);
            // 将给定整数写入此缓冲区的当前位置，当前位置递增
            buffer.put(j);
        }

        // 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为0
        buffer.flip();

        // 查看在当前位置和限制位置之间是否有元素
        while (buffer.hasRemaining()) {
            // 读取此缓冲区当前位置的整数，然后当前位置递增
            int j = buffer.get();
            System.out.print(j + "  ");
        }

    }
    static public void channel( String args[] ) throws Exception {
        FileInputStream fin = new FileInputStream("c:\\test.txt");
        // 获取通道
        FileChannel fc = fin.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        while (buffer.remaining()>0) {
            byte b = buffer.get();
            System.out.print(((char)b));
        }

        fin.close();
    }


}
