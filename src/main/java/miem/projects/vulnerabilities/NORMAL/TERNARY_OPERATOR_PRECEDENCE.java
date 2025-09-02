package miem.projects.vulnerabilities.NORMAL;

import java.lang.management.ManagementFactory;

public class TERNARY_OPERATOR_PRECEDENCE {

    public static void main(String[] args) {
        fooIncorrect();
        fooCorrect();
    }

    public static void fooIncorrect() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String text = "process name " + name == null ? "" : name;
        System.out.println(text);
    }

    public static void fooCorrect() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String text = "process name " + (name == null ? "" : name);
        System.out.println(text);
    }
}
