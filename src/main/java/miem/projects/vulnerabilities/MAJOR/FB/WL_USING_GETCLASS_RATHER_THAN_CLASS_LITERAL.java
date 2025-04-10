package miem.projects.vulnerabilities.MAJOR.FB;

public class WL_USING_GETCLASS_RATHER_THAN_CLASS_LITERAL {
    public static void main(String[] args) {
        WL_USING_GETCLASS_RATHER_THAN_CLASS_LITERAL test = new WL_USING_GETCLASS_RATHER_THAN_CLASS_LITERAL();
        test.incorrectTest();
        test.correctTest();
    }

    public void incorrectTest() {
        synchronized (this.getClass()) {
            System.out.println("...");
        }
    }

    public void correctTest() {
        synchronized (WL_USING_GETCLASS_RATHER_THAN_CLASS_LITERAL.class) {
            System.out.println("...");
        }
    }
}
