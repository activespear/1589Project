package miem.projects.vulnerabilities.MAJOR.FB;

public class SBSC_USE_STRINGBUFFER_CONCATENATION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String result = "";
        for (int i = 0; i < 1000; i++) {
            // На каждой итерации создается новая строка!
            result += i + ",";
        }
        System.out.println(result);
    }

    public static void correctTest() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            result.append(i).append(",");
        }
        System.out.println(result);
    }
}