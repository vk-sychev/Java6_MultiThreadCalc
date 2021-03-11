package com.company;


import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class LruCacheFactory<K, V> {
    private LruCacheImpl<K, V> lruCache;
    public LruCacheImpl<K, V> getLruCache() {
        return lruCache;
    }

    public ILruCache createLruCache () {
        lruCache = new LruCacheImpl<>(5);
        ClassLoader lruCacheClassLoader = lruCache.getClass().getClassLoader();
        Class[] interfaces = lruCache.getClass().getInterfaces();
        ILruCache proxyLruCache = (ILruCache) Proxy.newProxyInstance(lruCacheClassLoader, interfaces, new LruCacheInvocationHandler(lruCache));
        return proxyLruCache;
    }
}
