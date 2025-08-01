package miem.projects.vulnerabilities.MAJOR.FB;

import java.math.BigDecimal;

public class DMI_BIGDECIMAL_CONSTRUCTED_FROM_DOUBLE {

    // Небезопасный способ — конструктор BigDecimal(double)
    public static void unsafeBigDecimal() {
        BigDecimal value = new BigDecimal(0.1);
        System.out.println("Unsafe BigDecimal from double: " + value);
    }

    // Безопасный способ — BigDecimal.valueOf(double)
    public static void safeBigDecimal() {
        BigDecimal value = BigDecimal.valueOf(0.1);
        System.out.println("Safe BigDecimal from valueOf: " + value);
    }

    public static void main(String[] args) {
        unsafeBigDecimal();
        safeBigDecimal();
    }
}

