package miem.projects.vulnerabilities.MINOR.FB;

public class FB_IM_MULTIPLYING_RESULT_OF_IREM {

    public void example() {
        int i = 5;

        int resultUnsafe = i % 60 * 1000;

        int resultSafe = (i % 60) * 1000;

        System.out.println("Unsafe result: " + resultUnsafe);
        System.out.println("Safe result: " + resultSafe);
    }
}
