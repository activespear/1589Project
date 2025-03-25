package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Arrays;

public class DMI_INVOKING_TOSTRING_ON_ANONYMOUS_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String s = new int[]{1, 2, 3}.toString();
    }

    public static void correctTest() {
        String s = Arrays.toString(new int[]{1, 2, 3});
    }
}