package miem.projects.vulnerabilities.NORMAL.FB;

public class DCN_NULLPOINTER_EXCEPTION {
    public static void main(String[] args) {
        System.out.println("incorrectTest: " + incorrectTest(null));
        System.out.println("correctTest: " + correctTest(null));
    }

    public static boolean incorrectTest(String m) {
        try {
            String[] ms = m.split(" ");
            return ms.length != 1;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean correctTest(String m) {
        if (m == null) return false;
        String[] ms = m.split(" ");
        return ms.length != 1;
    }
}