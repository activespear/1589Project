package miem.projects.vulnerabilities.MINOR.FB;

public class NM_BAD_EQUAL {
    static class IncorrectExample {
        private int id;
        
        // Неправильное имя метода
        public boolean equal(Object o) {  // FB.NM_BAD_EQUAL
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IncorrectExample that = (IncorrectExample) o;
            return id == that.id;
        }
    }
    
    static class CorrectExample {
        private int id;
        
        // Правильное имя метода
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CorrectExample that = (CorrectExample) o;
            return id == that.id;
        }
    }

    public static void main(String[] args) {
        IncorrectExample a = new IncorrectExample();
        IncorrectExample b = new IncorrectExample();
        
        // Сравнение не будет работать ожидаемым образом
        System.out.println("Incorrect equals: " + a.equals(b));  // false (вызов Object.equals())
        System.out.println("Incorrect equal: " + a.equal(b));    // true
        
        CorrectExample x = new CorrectExample();
        CorrectExample y = new CorrectExample();
        
        System.out.println("Correct equals: " + x.equals(y));    // true
    }
}