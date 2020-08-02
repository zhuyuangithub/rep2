package com.atguigu.design.composite;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
       //保证前面的线程都执行完
        while(Thread.activeCount()>2) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }

        System.out.println(test.inc);
    }
}