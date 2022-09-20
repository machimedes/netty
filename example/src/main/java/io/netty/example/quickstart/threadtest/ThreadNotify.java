package io.netty.example.quickstart.threadtest;

public class ThreadNotify {

    public static void main(String[] args) throws Exception {


        final ThreadSyncable t1 = new ThreadSyncable(new Runnable() {
            @Override
            synchronized public void run() {
                int i = 0;
                while (i++ < 5) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1");
                }
                System.out.println("t1 通知 t2 t3 运行");
                this.notify();
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(1000);
                    System.out.println("t2 -----");
                    System.out.println("t2 等待 t1 完成");
                    t1.sync();
                    while (i++ < 3) {
                        Thread.sleep(1000);
                        System.out.println("t2 +++++" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(1000);
                    System.out.println("t3 -----");
                    System.out.println("t3 等待 t1 完成");
                    t1.sync();
                    while (i++ < 3) {
                        Thread.sleep(1000);
                        System.out.println("t3 +++++" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

class ThreadSyncable extends Thread {
    public ThreadSyncable(Runnable t1) {
        super(t1);
    }

    synchronized public void sync() throws InterruptedException {
        this.wait();
    }
}
