package miem.projects.vulnerabilities.MAJOR.FB;

public class RE_BAD_SYNTAX_FOR_REGULAR_EXPRESSION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "abc123";
        // Неэкранированные спецсимволы
        var flag = str.matches(".*+.*"); // true
    }

    public static void correctTest() {
        String str = "abc123";
        var flag = str.matches(".*\\+.*"); // false
    }
}