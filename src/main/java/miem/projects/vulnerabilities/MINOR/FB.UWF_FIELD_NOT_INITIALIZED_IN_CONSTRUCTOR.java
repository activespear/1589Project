package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class UserProfile {
            private String username;
            
            // Некорректно: поле не инициализировано в конструкторе
            public UserProfile() {}
            
            public String getUsername() {
                return username;  // Может вернуть null
            }
        }
        
        UserProfile profile = new UserProfile();
        System.out.println("Username: " + profile.getUsername());
    }

    public static void correctTest() {
        class UserProfile {
            private String username;
            
            // Корректно: явная инициализация поля
            public UserProfile() {
                this.username = "default";
            }
            
            public String getUsername() {
                return username;
            }
        }
        
        UserProfile profile = new UserProfile();
        System.out.println("Username: " + profile.getUsername());
    }
}