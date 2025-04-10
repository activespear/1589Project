package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_GC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // ...
        // явное принудительное освобождение памяти
        System.gc();
    }

    public static void correctTest() {
        // Единственный оправданный случай —
        // бенчмаркинг перед замером потребления памяти.
        System.gc(); // Освобождаем память перед замером
        long beforeMemory = Runtime.getRuntime().freeMemory();

        byte[] array = new byte[10_000_000];

        long afterMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Memory used: " + (beforeMemory - afterMemory) + " bytes");
    }
}