package miem.projects.vulnerabilities.MINOR.FB;

public class FB_FL_MATH_USING_FLOAT_PRECISION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: использование float для точных вычислений
        float accountBalance = 100.00f;
        for (int i = 0; i < 100; i++) {
            accountBalance += 0.01f;  // Накопление ошибки округления
        }
        System.out.println("Incorrect balance: " + accountBalance);
    }

    public static void correctTest() {
        // Корректно: использование BigDecimal для точных вычислений
        java.math.BigDecimal accountBalance = new java.math.BigDecimal("100.00");
        java.math.BigDecimal increment = new java.math.BigDecimal("0.01");
        
        for (int i = 0; i < 100; i++) {
            accountBalance = accountBalance.add(increment);
        }
        System.out.println("Precise balance: " + accountBalance);
        
        // Альтернатива с double (для менее критичных вычислений)
        double altBalance = 100.00;
        for (int i = 0; i < 100; i++) {
            altBalance += 0.01;
        }
        System.out.println("Double balance: " + altBalance);
    }
}