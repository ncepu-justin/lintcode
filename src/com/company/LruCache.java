package com.company;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU算法实现
 * Created by Justin
 * 2019/4/9  16:03
 */
public class LruCache {

    private Lru cache;

    public LruCache(int capacity){
        this.cache=new Lru(capacity);
    }

    public int get(int key){
        Integer result=cache.get(key);
        if(result==null){
            return -1;
        }
        return result;
    }

    public void put(int key,int value){
        cache.put(key,value);
    }


    private class Lru extends LinkedHashMap<Integer,Integer>{

        private Integer capacity;

        private Lru(Integer capacity){
            super(capacity,0.75f,true);
            this.capacity=capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return this.size()>capacity;
        }
    }
}
