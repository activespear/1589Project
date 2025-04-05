package miem.projects.vulnerabilities.MINOR.FB;

public class RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE {
    public static void main(String[] args) {
        redundantCheck("test");
        correctCheck("test");
    }

    // Некорректный пример
    public static void redundantCheck(String input) {
        char firstChar = input.charAt(0);  // NPE здесь, если input null
        if (input != null) {               // FB.RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE
            System.out.println("First char: " + firstChar);
        }
    }

    // Корректные примеры
    public static void correctCheck(String input) {
        // Вариант 1: Проверка до разыменования
        if (input != null && !input.isEmpty()) {
            char firstChar = input.charAt(0);
            System.out.println("First char: " + firstChar);
        }
        
        // Вариант 2: Допускаем NPE, если контракт метода запрещает null
        char firstChar = input.charAt(0);
        System.out.println("First char: " + firstChar);
    }
}