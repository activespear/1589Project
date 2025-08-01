package miem.projects.vulnerabilities.MAJOR.FB;

public class CNT_ROUGH_CONSTANT_VALUE {

    // Небезопасная версия с "жёстко прописанным" значением π
    public static class UnsafeCircle {
        public double calculateArea(double radius) {
            return 3.14159 * radius * radius;
        }
    }

    // Безопасная версия с использованием Math.PI
    public static class SafeCircle {
        public static final double PI = Math.PI;

        public double calculateArea(double radius) {
            return PI * radius * radius;
        }
    }

    public static void runUnsafe() {
        UnsafeCircle circle = new UnsafeCircle();
        double area = circle.calculateArea(10);
        System.out.println("Unsafe area calculation: " + area);
    }

    public static void runSafe() {
        SafeCircle circle = new SafeCircle();
        double area = circle.calculateArea(10);
        System.out.println("Safe area calculation: " + area);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe calculation:");
        runUnsafe();

        System.out.println("\nRunning safe calculation:");
        runSafe();
    }
}

