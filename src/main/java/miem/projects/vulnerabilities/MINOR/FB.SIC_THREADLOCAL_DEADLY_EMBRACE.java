package miem.projects.vulnerabilities.MINOR.FB;

import java.lang.ref.WeakReference;

public class SIC_THREADLOCAL_DEADLY_EMBRACE {
    public static void main(String[] args) {
        dangerousUsage();
        safeUsage();
    }
    
    // Некорректное использование
    public static void dangerousUsage() {
        OuterClass outer = new OuterClass();
        outer.initDangerous();
        // Потенциальная утечка памяти и deadlock
    }
    
    // Корректные альтернативы
    public static void safeUsage() {
        // Вариант 1: Статический внутренний класс
        OuterClass.StaticInner safe1 = new OuterClass.StaticInner();
        
        // Вариант 2: WeakReference
        ThreadLocal<WeakReference<OuterClass.StaticInner>> safeThreadLocal = 
            new ThreadLocal<>();
        safeThreadLocal.set(new WeakReference<>(safe1));
        
        // Вариант 3: Отдельный класс
        ThreadLocal<IndependentClass> safest = new ThreadLocal<>();
        safest.set(new IndependentClass());
    }
}

class OuterClass {
    private ThreadLocal<NonStaticInner> dangerousTL = new ThreadLocal<>();
    
    // Нестатический внутренний класс (опасно)
    class NonStaticInner {
        void work() {
            System.out.println("Non-static inner working");
        }
    }
    
    // Статический внутренний класс (безопасно)
    static class StaticInner {
        void work() {
            System.out.println("Static inner working");
        }
    }
    
    public void initDangerous() {
        dangerousTL.set(new NonStaticInner());  // FB.SIC_THREADLOCAL_DEADLY_EMBRACE
    }
}

class IndependentClass {
    public void execute() {
        System.out.println("Independent class working");
    }
}