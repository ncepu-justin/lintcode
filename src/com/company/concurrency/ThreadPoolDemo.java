package com.company.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static class MyTast implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
          try {
              Thread.sleep(1000);
          }catch (InterruptedException e){
              e.printStackTrace();
          }
        }

        public static void main(String[] args) {
            MyTast task=new MyTast();
            ExecutorService es= Executors.newCachedThreadPool();
            for (int i = 0; i <10 ; i++) {
                es.submit(task);
            }

        }
    }
}
