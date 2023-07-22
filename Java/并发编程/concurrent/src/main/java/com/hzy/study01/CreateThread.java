package com.hzy.study01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

/**
 * @title: CreateThread 创建线程
 * @Author zxwyhzy
 * @Date: 2023/7/21 22:22
 * @Version 1.0
 */
@Slf4j(topic = "c")
public class CreateThread {
    public static void main(String[] args) throws Exception{

        log.debug("主线程");
        t1();
        t2();
        t3();

    }

    public static void t1() {
/*        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.debug("子线程1");
            }
        };*/
        // lambda简化
        Thread t1 = new Thread(() -> log.debug("线程1"));
        t1.setName("t1");
        t1.start();
    }

    public static void t2() {
/*        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("子线程2");
            }
        };*/
        // lambda简化
        Runnable runnable = () -> log.debug("子线程2");
        Thread t2 = new Thread(runnable, "t2");
        // 再简化
        Thread t3 = new Thread(() -> log.debug("子线程3"), "t2");
        t2.start();
    }

    public static void t3() throws Exception {
/*        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("子线程4");
                Thread.sleep(2000);
                return 10;
            }
        });*/
        //  lambda 简化
        FutureTask<Integer> task = new FutureTask<>(() -> {
                log.debug("子线程4");
                Thread.sleep(2000);
                return 10;
        });
        Thread t4 = new Thread(task,"t4");
        t4.start();

        log.debug("线程4返回值:{}",task.get());
    }

}
