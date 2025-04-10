package miem.projects.vulnerabilities.MAJOR.FB;

public class RV_ABSOLUTE_VALUE_OF_HASHCODE {
    public static void main(String[] args) {
        incorrectTest(new Object());
        correctTest(new Object());
    }

    public static void incorrectTest(Object obj) {
        int hash = obj.hashCode();
        // Возможен Integer.MIN_VALUE:
        int absHash = Math.abs(hash);
    }

    public static void correctTest(Object obj) {
        int hash = obj.hashCode();
        int absHash = (hash == Integer.MIN_VALUE) ? 0 : Math.abs(hash);
    }
}