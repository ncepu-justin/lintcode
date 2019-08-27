package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Justin
 * 2019/7/23  21:51
 */
public class LRUcacheV1 {

    // LRU


    Map<String, LRUNode> map = new HashMap<>();
    LRUNode head;
    LRUNode tail;
    // 缓存最大容量，我们假设最大容量大于 1，
    // 当然，小于等于1的话需要多加一些判断另行处理
    int capacity;

    public LRUcacheV1(int capacity) {
        this.capacity = capacity;
    }

    public void put(String key, Object value) {
        //如果头结点为空，则创建新节点
        if (head == null) {
            head = new LRUNode(key, value);
            tail = head;
            map.put(key, head);
            return;
        }
        LRUNode node = map.get(key);
        if (node != null) {
            // 更新值
            node.value = value;
            // 把他从链表删除并且插入到头结点
            removeAndInsert(node);
        } else {
            LRUNode tmp = new LRUNode(key, value);
            // 如果会溢出
            if (map.size() >= capacity) {
                // 先把它从哈希表中删除
                map.remove(tail.key);
                // 删除尾部节点
                tail = tail.pre;
                if (tail != null)
                    tail.next = null;
            }
            map.put(key, tmp);
            // 插入
            tmp.next = head;
            head.pre = tmp;
            head = tmp;
        }
    }

    public Object get(String key) {
        LRUNode node = map.get(key);
        if (node != null) {
            // 把这个节点删除并插入到头结点
            removeAndInsert(node);
            return node.value;
        }
        return null;
    }

    private void removeAndInsert(LRUNode node) {
        // 特殊情况先判断，例如该节点是头结点或是尾部节点
        if (node == head) {
            return;
        } else if (node == tail) {
            tail = node.pre;
            if (tail != null)
                tail.next = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        // 插入到头结点
        node.next = head;
        node.pre = null;
        head.pre = node;
        head = node;
    }

}
