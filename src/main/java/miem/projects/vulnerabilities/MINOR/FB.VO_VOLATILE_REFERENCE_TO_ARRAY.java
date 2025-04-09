package miem.projects.vulnerabilities.MINOR.FB;

public class FB_VO_VOLATILE_REFERENCE_TO_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class ArrayHolder {
            private volatile int[] array = new int[10];
            
            public void updateArray() {
                for (int i = 0; i < array.length; i++) {
                    array[i] = i;  // Изменения элементов не гарантированно видны другим потокам
                }
            }
        }
        
        ArrayHolder holder = new ArrayHolder();
        Thread t1 = new Thread(holder::updateArray);
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.println("Incorrect array[5]: " + holder.array[5]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        t1.start();
        t2.start();
    }

    public static void correctTest() {
        class ArrayHolder {
            private final int[] array = new int[10];
            
            public synchronized void updateArray() {
                for (int i = 0; i < array.length; i++) {
                    array[i] = i;  // Синхронизированная модификация
                }
            }
            
            public synchronized int getValue(int index) {
                return array[index];  // Синхронизированное чтение
            }
        }
        
        ArrayHolder holder = new ArrayHolder();
        Thread t1 = new Thread(holder::updateArray);
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.println("Correct array[5]: " + holder.getValue(5));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        t1.start();
        t2.start();
    }
}