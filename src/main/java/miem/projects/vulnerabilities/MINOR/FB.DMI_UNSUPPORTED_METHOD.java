package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Date;

public class FB_DMI_UNSUPPORTED_METHOD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    @SuppressWarnings("deprecation")
    public static void incorrectTest() {
        // Некорректно: использование deprecated методов
        Date date = new Date();
        int year = date.getYear();  // Deprecated since Java 1.1
        int month = date.getMonth(); // Deprecated since Java 1.1
        
        System.out.println("Deprecated date: " + year + "-" + month);
    }

    public static void correctTest() {
        // Корректно: использование современных API
        java.time.LocalDate date = java.time.LocalDate.now();
        int year = date.getYear();
        java.time.Month month = date.getMonth();
        
        System.out.println("Modern date: " + year + "-" + month.getValue());
        
        // Альтернатива с Calendar (менее предпочтительно)
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int calYear = cal.get(java.util.Calendar.YEAR);
        int calMonth = cal.get(java.util.Calendar.MONTH) + 1;
        
        System.out.println("Calendar date: " + calYear + "-" + calMonth);
    }
}