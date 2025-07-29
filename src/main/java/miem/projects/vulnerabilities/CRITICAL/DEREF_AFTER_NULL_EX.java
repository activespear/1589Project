package miem.projects.vulnerabilities.CRITICAL;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DEREF_AFTER_NULL_EX {

    // Небезопасная операция
    public static class UnsafeDateParser {
        public static Date parseUnsafe(String date) throws ParseException {
            System.out.println("\n[UNSAFE] Attempting to parse date");
            if (date != null) {
                System.out.println("Parsing: '" + date + "'");
            } else {
                System.out.println("Warning: null date input (but will still try to parse)");
            }

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA);
            return df.parse(date);  // Может вызвать NullPointerException
        }
    }

    // Безопасная операция
    public static class SafeDateParser {
        public static Date parseSafe(String date) throws ParseException, IllegalArgumentException {
            System.out.println("\n[SAFE] Attempting to parse date");
            if (date == null) {
                throw new IllegalArgumentException("Date string must not be null");
            }
            System.out.println("Parsing: '" + date + "'");

            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA);
            return df.parse(date);
        }
    }

    public static void main(String[] args) {
        System.out.println("DEREF_AFTER_NULL_EX DEMONSTRATION");

        // Тестовые данные
        String[] testDates = {
                "2023년 12월 31일",  // Корейский формат даты
                null,
                "2024년 1월 1일"
        };

        // Тестирование парсеров
        for (String dateStr : testDates) {
            System.out.println("\n=== Testing with: " + (dateStr == null ? "null" : "'" + dateStr + "'") + " ===");

            // Тест небезопасного парсера
            try {
                Date result = UnsafeDateParser.parseUnsafe(dateStr);
                System.out.println("Unsafe parse success: " + result);
            } catch (NullPointerException e) {
                System.out.println("Unsafe parse failed with NullPointerException");
            } catch (ParseException e) {
                System.out.println("Unsafe parse failed with ParseException: " + e.getMessage());
            }

            // Тест безопасного парсера
            try {
                Date result = SafeDateParser.parseSafe(dateStr);
                System.out.println("Safe parse success: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Safe parse rejected input: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Safe parse failed with ParseException: " + e.getMessage());
            }
        }

        // Дополнительные тесты с неверными форматами
        System.out.println("\n=== Additional Format Tests ===");
        String invalidDate = "31/12/2023";  // Неправильный формат для корейской локали

        try {
            System.out.println("Testing invalid format with safe parser");
            Date result = SafeDateParser.parseSafe(invalidDate);
            System.out.println("Unexpected success: " + result);
        } catch (Exception e) {
            System.out.println("Correctly failed with: " + e.getClass().getSimpleName() +
                    " - " + e.getMessage());
        }
    }
}
