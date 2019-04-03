package com.ccnu.test;

public class LocalCacheTest {

    public static void main(String[] args) {
        LocalCache localCache = new LocalCache(20, 10);

        for (int i = 0; i < 25; i++) {
            localCache.put("key" + i, "value" + i);
        }
        System.out.println(localCache);
    }
}
