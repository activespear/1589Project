package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CO_COMPARETO_INCORRECT_FLOATING {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class FloatingPoint implements Comparable<FloatingPoint> {
            double value;
            
            public FloatingPoint(double value) {
                this.value = value;
            }
            
            // Некорректно: прямое сравнение double через ==
            @Override
            public int compareTo(FloatingPoint other) {
                if (this.value == other.value) {  // Опасное сравнение
                    return 0;
                }
                return this.value < other.value ? -1 : 1;
            }
        }
        
        FloatingPoint fp1 = new FloatingPoint(0.1 + 0.2);
        FloatingPoint fp2 = new FloatingPoint(0.3);
        
        // Может дать неожиданный результат из-за погрешности double
        System.out.println("Incorrect comparison: " + fp1.compareTo(fp2));
    }

    public static void correctTest() {
        class FloatingPoint implements Comparable<FloatingPoint> {
            double value;
            private static final double EPSILON = 1e-10;
            
            public FloatingPoint(double value) {
                this.value = value;
            }
            
            // Корректно: сравнение с учетом погрешности
            @Override
            public int compareTo(FloatingPoint other) {
                if (Math.abs(this.value - other.value) < EPSILON) {
                    return 0;
                }
                return this.value < other.value ? -1 : 1;
            }
        }
        
        FloatingPoint fp1 = new FloatingPoint(0.1 + 0.2);
        FloatingPoint fp2 = new FloatingPoint(0.3);
        
        System.out.println("Correct comparison: " + fp1.compareTo(fp2));
    }
}