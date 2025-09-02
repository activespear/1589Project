package miem.projects.vulnerabilities.NORMAL.FB;

import java.util.HashMap;
import java.util.Map;

public class SSD_DO_NOT_USE_INSTANCE_LOCK_ON_SHARED_STATIC_DATA {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class UnsafeCache {
            private static Map<String, Object> CACHE = new HashMap<>();
            private final Object lock = new Object();

            public void addToCache(String key, Object value) {
                synchronized (lock) {
                    CACHE.put(key, value);
                }
            }
        }

        UnsafeCache cache1 = new UnsafeCache();
        UnsafeCache cache2 = new UnsafeCache();
        cache1.addToCache("a", 1);
        cache2.addToCache("b", 2);
        System.out.println("Incorrect cache: " + UnsafeCache.CACHE);
    }

    public static void correctTest() {
        class SafeCache {
            private static Map<String, Object> CACHE = new HashMap<>();
            private static final Object STATIC_LOCK = new Object();

            public void addToCache(String key, Object value) {
                synchronized (STATIC_LOCK) {
                    CACHE.put(key, value);
                }
            }
        }

        SafeCache cache1 = new SafeCache();
        SafeCache cache2 = new SafeCache();
        cache1.addToCache("x", 100);
        cache2.addToCache("y", 200);
        System.out.println("Correct cache: " + SafeCache.CACHE);
    }
}
