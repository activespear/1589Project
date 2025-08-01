package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Calendar;

public class DMI_BAD_MONTH {

    // Небезопасная функция — устанавливает месяц без проверки
    public static void unsafeSetMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Unsafe: setting month to " + month);
        calendar.set(Calendar.MONTH, month);
        System.out.println("Resulting month in calendar: " + calendar.get(Calendar.MONTH));
    }

    // Безопасная функция — проверяет месяц перед установкой
    public static void safeSetMonth(int month) {
        if (month >= 0 && month <= 11) {
            Calendar calendar = Calendar.getInstance();
            System.out.println("Safe: setting month to " + month);
            calendar.set(Calendar.MONTH, month);
            System.out.println("Resulting month in calendar: " + calendar.get(Calendar.MONTH));
        } else {
            System.out.println("Некорректное значение месяца: " + month);
        }
    }

    public static void main(String[] args) {
        int month = 15;

        unsafeSetMonth(month);
        safeSetMonth(month);
    }
}

