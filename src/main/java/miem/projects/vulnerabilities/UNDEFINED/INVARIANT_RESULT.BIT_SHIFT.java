package miem.projects.vulnerabilities.MINOR.FB;

public class INVARIANT_RESULT_BIT_SHIFT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректный сдвиг с инвариантным результатом
    public static void incorrectTest() {
        int value = 0x01;
        int shifted = value << 32;  // ОШИБКА: сдвиг на 32 бита для int
        System.out.println("Результат сдвига (некорректный): " + shifted);
    }

    // Корректная операция сдвига
    public static void correctTest() {
        int value = 0x01;
        int shifted = value << 3;  // Корректный сдвиг на 3 бита
        System.out.println("Результат сдвига (корректный): " + shifted);
    }
}