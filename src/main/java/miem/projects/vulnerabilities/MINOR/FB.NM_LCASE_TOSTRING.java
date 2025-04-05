package miem.projects.vulnerabilities.MINOR.FB;

public class NM_LCASE_TOSTRING {
    static class IncorrectToString {
        private int id;
        private String label;
        
        // Неправильное имя метода
        public String tostring() {  // FB.NM_LCASE_TOSTRING
            return "Item #" + id;
        }
    }
    
    static class CorrectToString {
        private int id;
        private String label;
        
        // Правильное имя метода
        @Override
        public String toString() {
            return String.format("Item #%d (%s)", id, label);
        }
    }

    public static void main(String[] args) {
        IncorrectExample bad = new IncorrectExample();
        CorrectExample good = new CorrectExample();
        
        // Автоматический вызов toString()
        System.out.println("Bad: " + bad);  // Вызовет Object.toString()
        System.out.println("Good: " + good); // Вызовет переопределенный toString()
        
        // Явный вызов
        System.out.println("Bad explicit: " + bad.tostring());  // Работает, но неправильно
        System.out.println("Good explicit: " + good.toString()); // Корректно
    }
}