package miem.projects.vulnerabilities.NORMAL;

public class DOUBLE_CLOSE_PROC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            Process process = new ProcessBuilder("java", "-version").start();
            process.waitFor();
            process.destroy();
            process.destroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try {
            Process process = new ProcessBuilder("java", "-version").start();
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}