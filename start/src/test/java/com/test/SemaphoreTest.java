package com.test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    static Semaphore sm = new Semaphore(3);

    public static void main(String[] args) {
        //参考https://blog.csdn.net/qq_35597828/article/details/121052805
        for (int i = 0; i < 100; i++) {

            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName()+"开始停车");
                    /*if (sm.availablePermits() == 0){
                        System.out.println(Thread.currentThread().getName()+"没有车位啦");
                        return;
                    }*/

                    boolean getToken = sm.tryAcquire();
                    System.out.println(Thread.currentThread().getName()+":获取车位结果："+getToken);
                    if (!getToken){
                        System.out.println(Thread.currentThread().getName()+":没有车位啦,去别的停车场吧");
                        return;
                    }

                    System.out.println("可用的令牌数量："+sm.availablePermits());

//                    sm.acquire();

                    int i1 = new Random().nextInt(10);

                    System.out.println(Thread.currentThread().getName() + "停" + i1 + "秒" );

                    TimeUnit.SECONDS.sleep(i1);
                    sm.release();
                    System.out.println(Thread.currentThread().getName() + "停车完毕");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            },i+"号车").start();


        }


    }
}
