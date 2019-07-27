package com.company;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import java.util.HashMap;

/**
 * LRU算法实现--基于map+双端链表  记录head和tail节点  保证O（1）时间复杂度
 * 对于 put 操作，会出现以下几种情况：
 * <p>
 * 1、如果要 put(key,value) 已经存在于链表之中了（根据key来判断），那么我们需要把链表中旧的数据删除，然后把新的数据插入到链表的头部。
 * 2、如果要 put(key,value) 的数据没有存在于链表之后，我们我们需要判断缓存区是否已满，如果满的话，则把链表尾部的节点删除，之后把新的数据插入到链表头部。
 * 如果没有满的话，直接把数据插入链表头部即可。
 * <p>
 * 对于 get 操作，则会出现以下情况
 * 1、如果要 get(key) 的数据存在于链表中，则把 value 返回，并且把该节点删除，删除之后把它插入到链表的头部（表示最新用到了该节点数据）。
 * 2、如果要 get(key) 的数据不存在于链表之后，则直接返回 -1 即可。
 * <p>
 * Created by Justin
 * 2019/4/9  16:03
 */
public class LRUCache {

    private static class LruNode {
        private int key;
        private int value;
        private LruNode pre;
        private LruNode next;

        public LruNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private LruNode head;
    private LruNode tail;

    private HashMap<Integer, LruNode> lruNodeHashMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        LruNode resNode = lruNodeHashMap.get(key);
        if (resNode != null) {
            removeAndInsert(resNode);
            return resNode.value;
        }
        return -1;
    }

    private void removeAndInsert(LruNode resNode) {
        //如果是头结点则什么都不做
        if (resNode == head)
            return;
        else if (resNode == tail) {
            tail = tail.pre;
            if (tail != null)
                tail.next = null;
        } else {
            resNode.pre.next = resNode.next;
            //注意变量赋值顺序(容易犯错)
            resNode.next.pre = resNode.pre;
        }
        //插入头结点
        resNode.next = head;
        head.pre = resNode;
        head = resNode;
    }

    public void put(int key, int value) {
        //如果头结点为空，则插入
        if (head == null) {
            head = new LruNode(key, value);
            tail = head;
            lruNodeHashMap.put(key, head);
            return;
        }
        LruNode insertNode = lruNodeHashMap.get(key);
        //如果要插入的节点存在的话,删除现在节点并插入头结点
        if (insertNode != null) {
            insertNode.value = value;
            removeAndInsert(insertNode);
        } else {
            //如果插入节点不存在的话，先判断是否超过目前容量，如果超过的话就先删除尾结点，然后在插入头结点
            if (lruNodeHashMap.size() >= capacity) {
                lruNodeHashMap.remove(tail.key);
                //删除尾结点--如果只剩一个尾结点,注意尾结点判空操作，防止空指针
                tail = tail.pre;
                if (tail != null)
                    tail.next = null;
            }
            LruNode tmp = new LruNode(key, value);
            lruNodeHashMap.put(key, tmp);
            tmp.next = head;
            head.pre = tmp;
            head = tmp;
        }
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        lruCache.put(5, 5);
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println("----------------------------------");
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.lruNodeHashMap.keySet());
        System.out.println(lruCache.get(5));
        System.out.println(lruCache.lruNodeHashMap.keySet());
    }
}
