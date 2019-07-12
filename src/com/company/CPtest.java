package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者-消费者模式 通过阻塞队列BlockingQueue 实现
 * Created by Justin
 * 2019/4/10  17:45
 */
public class CPtest {

    private static class Producer implements Runnable{

        BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            try {
                String temp="A produce:"+Thread.currentThread().getName();
                System.out.println("A has produce temp:"+temp);
                queue.put(temp);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static class Consumer implements Runnable{

        BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            try {
                String temp=queue.take();
                System.out.println(temp);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue=new LinkedBlockingQueue<String>(2);
        Consumer consumer=new Consumer(queue);
        Producer producer=new Producer(queue);
        for (int i = 0; i <5; i++) {
                    new Thread(producer,"Producer"+(i+1)).start();
                    new Thread(consumer,"Consumer"+(i+1)).start();
                }
    }
}
