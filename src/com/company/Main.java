package com.company;

import java.lang.reflect.Proxy;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //LruCacheImpl<Integer, String> lruCache = new LruCacheImpl<>(5);
        LruCacheFactory factory = new LruCacheFactory();
        ILruCache<Integer, String> proxyLruCache = factory.createLruCache();

        proxyLruCache.set(1,"AMM");
        proxyLruCache.set(1,"AMM");
        proxyLruCache.set(2,"CSF");
        proxyLruCache.set(3,"MathFac");
        proxyLruCache.set(4,"PhysicsFac");
        proxyLruCache.set(5,"ChemistryFac");

        printCache(factory.getLruCache());

        proxyLruCache.get(1);
        proxyLruCache.get(2);

        printCache(factory.getLruCache());

        proxyLruCache.set(6,"FarmFac");
        proxyLruCache.set(7,"Borisoglebsk");
/*        lruCache.set(1,"AMM");
        lruCache.set(2,"CSF");
        lruCache.set(3,"MathFac");
        lruCache.set(4,"PhysicsFac");
        lruCache.set(5,"ChemistryFac");
        lruCache.get(1);
        lruCache.get(2);*/

/*        lruCache.set(6,"FarmFac");
        lruCache.set(7,"Borisoglebsk");*/
//        lruCache.set(7,"MBF");
        printCache(factory.getLruCache());
    }

    private static void printCache(LruCacheImpl<Integer, String> lruCache) {
        System.out.println();
        for(Map.Entry<Integer, String> item : lruCache.getEntrySet()){
            System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
        }
        System.out.println();
    }
}
