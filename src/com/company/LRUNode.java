package com.company;

/**
 * Created by Justin
 * 2019/7/23  21:53
 */
public class LRUNode {
    String key;
    Object value;
    LRUNode next;
    LRUNode pre;

    public LRUNode(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
