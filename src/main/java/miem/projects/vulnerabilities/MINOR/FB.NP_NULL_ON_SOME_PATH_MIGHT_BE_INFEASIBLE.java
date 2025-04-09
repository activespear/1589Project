package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_NULL_ON_SOME_PATH_MIGHT_BE_INFEASIBLE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Пример, где анализатор может ошибочно предупредить о возможном NPE
        String result = alwaysReturnsNonNull().toUpperCase();
        System.out.println("Result: " + result);
    }

    public static void correctTest() {
        // Корректная реализация (хотя проверка избыточна)
        String value = alwaysReturnsNonNull();
        System.out.println("Result: " + value.toUpperCase());
    }

    private static String alwaysReturnsNonNull() {
        // Метод, который гарантированно возвращает не-null значение
        return "Always non-null value";
    }
}