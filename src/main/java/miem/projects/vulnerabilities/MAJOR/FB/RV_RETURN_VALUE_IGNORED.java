package miem.projects.vulnerabilities.MAJOR.FB;

public class RV_RETURN_VALUE_IGNORED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "WORD";
        str.toLowerCase();
    }

    public static void correctTest() {
        String str = "WORD";
        String lowerCase = str.toLowerCase();
    }
}
