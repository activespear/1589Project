package miem.projects.vulnerabilities.MINOR.FB;

public class FB_INT_BAD_COMPARISON_WITH_INT_VALUE {

    public void example() {
        int x = 5;
        if (x > 2147483648L) {
            System.out.println("x больше 2147483648");
        } else {
            System.out.println("x меньше или равно 2147483648");
        }
    }
}
