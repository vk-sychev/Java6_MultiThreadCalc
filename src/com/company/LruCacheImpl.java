package com.company;

import java.util.*;

public class LruCacheImpl<K, V> implements ILruCache<K, V> {
    private int maxSize;
    private Map<K, V> linkedHashMap;
    public Set<Map.Entry<K,V>> getEntrySet() {
        return linkedHashMap.entrySet();
    }
    private Map.Entry<K, V> eldestElem;
    public Map.Entry<K, V> getEldestElem() {
        return eldestElem;
    }
    private List<K> keyStoryList = new ArrayList<K>();
    public List<K> getKeyStoryList() {
        return  keyStoryList;
    }

    public LruCacheImpl(int maxSize) {
        this.maxSize = maxSize;
        linkedHashMap = new LinkedHashMap<>(maxSize,0.75f,true) {
            @Override
            public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                eldestElem = eldest;
                return checkCacheSizeAfterSet();
            }
        };
    }

    @Override
    public V get(K key) {
        keyStoryList.add(key);
        return linkedHashMap.get(key);
    }

    @Override
    public void set(K key, V value) {
        keyStoryList.add(key);
        linkedHashMap.put(key,value);
    }

    @Override
    public int getSize() {
        return linkedHashMap.size();
    }

    @Override
    public int getLimit() {
        return this.maxSize;
    }

    public boolean checkCacheSizeBeforeSet() { return linkedHashMap.size()+1 > maxSize; }
    public boolean checkCacheSizeAfterSet() { return linkedHashMap.size() > maxSize; }
}
