package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class LruCacheInvocationHandler implements InvocationHandler
{
    private LruCacheImpl lruCache;

    public LruCacheInvocationHandler (LruCacheImpl lruCache)
    {
        this.lruCache = lruCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        method.invoke(lruCache, args);
        switch (method.getName()) {
            case "set":
                if (lruCache.checkCacheSizeBeforeSet()) {
                    System.out.println("The eldest elem: " + lruCache.getEldestElem());
                }
                break;
            case "get":
                printList();
                break;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println();
        return null;
    }

    private void printList() {

        var list = lruCache.getKeyStoryList();
        int length = list.stream().toArray().length;

        int startPos = 0;
        if (length > 10)
            startPos = length - 10;

        System.out.println();
        System.out.println("История вызовов ключей\n");

        for (int i = startPos; i < length; i++) {
            System.out.println(list.get(i));
        }

        System.out.println();
    }
}
