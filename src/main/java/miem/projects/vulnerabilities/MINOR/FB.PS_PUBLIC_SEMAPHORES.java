package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.Semaphore;

public class FB_PS_PUBLIC_SEMAPHORES {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: публичный семафор
        class ResourcePool {
            public final Semaphore available = new Semaphore(3);  // Опасный публичный доступ
            
            public void useResource() throws InterruptedException {
                available.acquire();
                try {
                    System.out.println("Resource used by " + Thread.currentThread().getName());
                    Thread.sleep(100); // Имитация работы
                } finally {
                    available.release();
                }
            }
        }
        
        ResourcePool pool = new ResourcePool();
        // Внешний код может нарушить работу семафора
        pool.available.release(10); // Несанкционированное увеличение разрешений
    }

    public static void correctTest() {
        // Корректно: инкапсулированный семафор
        class SecureResourcePool {
            private final Semaphore available = new Semaphore(3);
            
            public void useResource() throws InterruptedException {
                available.acquire();
                try {
                    System.out.println("Resource used by " + Thread.currentThread().getName());
                    Thread.sleep(100);
                } finally {
                    available.release();
                }
            }
            
            // Контролируемый доступ к состоянию
            public int availablePermits() {
                return available.availablePermits();
            }
        }
        
        SecureResourcePool securePool = new SecureResourcePool();
        // Внешний код не может нарушить работу семафора
    }
}