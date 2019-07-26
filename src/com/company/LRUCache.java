package com.company;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import java.util.HashMap;

/**
 * LRU算法实现
 * Created by Justin
 * 2019/4/9  16:03
 */
public class LRUCache {

    private static class LruNode{
        private int key;
        private int value;
        private LruNode pre;
        private LruNode next;

        public LruNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capaity;
    private HashMap<Integer,LruNode> lruMap=new HashMap<>();
    private LruNode head;
    private LruNode tail;

    public LRUCache(int capacity) {
        this.capaity=capacity;
    }

    public int get(int key) {
        LruNode res=lruMap.get(key);
        if (res!=null){
           removeAndInsert(res);
           return res.value;
        }
        return -1;
    }

    private void removeAndInsert(LruNode res) {
         //如果是头结点则什么都不做
          if (res==head){
              return;
          }else if (res==tail){
              tail=tail.pre;
              tail.next=null;
          }else {
              res.pre.next=res.next;
              res.next.pre=res.pre;
          }
          //插入头结点
          res.next=head;
          res.pre=null;
          head.pre=res;
          head=res;
    }

    public void put(int key, int value) {
         //如果链表为空
         if (head==null){
             head=new LruNode(key,value);
             tail=head;
             lruMap.put(key,head);
             return;
         }
         LruNode res=lruMap.get(key);
         //如果插入的的值已存在,则删除该结点，然后在头结点插入
         if (res!=null){
             //更新value
             res.value=value;
             removeAndInsert(res);
         }else {
             //如果插入的值不存在的话，先判断是否超过容量
             if (lruMap.size()>=capaity){
                 //如果是大于容量，则先删除尾结点
                 lruMap.remove(tail.key);
                 tail=tail.pre;
                 tail.next=null;
             }
             lruMap.put(key,res);
             //插入头结点
             res=head.pre;
             res.next=head;
             head=res;
         }
    }


    public static void main(String[] args) {
        LRUCache lruCache=new LRUCache(5);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(2);
        lruCache.put(3,3);
        lruCache.put(3,4);
        lruCache.put(4,4);
        lruCache.put(5,5);
        lruCache.get(3);
    }
}
