package miem.projects.vulnerabilities.MAJOR.FB;

public class RE_POSSIBLE_UNINTENDED_PATTERN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "abc";
        var flag = str.matches("a.c"); // true
    }

    public static void correctTest() {
        String str = "abc";
        var flag = str.matches("a\\.c"); // false
    }
}