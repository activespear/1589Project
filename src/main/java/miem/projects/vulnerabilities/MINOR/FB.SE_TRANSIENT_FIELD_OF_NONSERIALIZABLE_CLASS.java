package miem.projects.vulnerabilities.MINOR.FB;

public class FB_SE_TRANSIENT_FIELD_OF_NONSERIALIZABLE_CLASS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: transient в несериализуемом классе
        class UserData {
            private transient String password;  // Избыточно
            
            public UserData(String password) {
                this.password = password;
            }
        }
        
        UserData data = new UserData("secret");
        System.out.println("Created user data (incorrect)");
    }

    public static void correctTest() {
        // Корректно: удаление transient из несериализуемого класса
        class UserData {
            private String password;  // Просто поле
            
            public UserData(String password) {
                this.password = password;
            }
        }
        
        UserData data = new UserData("secret");
        System.out.println("Created user data (correct)");
    }
}