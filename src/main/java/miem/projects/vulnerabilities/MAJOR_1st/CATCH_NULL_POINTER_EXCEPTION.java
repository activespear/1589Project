package miem.projects.vulnerabilities.MAJOR_1st;

public class CATCH_NULL_POINTER_EXCEPTION {

    // Вспомогательные классы
    static class User {
        private Profile profile;

        public User(Profile profile) {
            this.profile = profile;
        }

        public Profile getProfile() {
            return profile;
        }
    }

    static class Profile {
        private String name;

        public Profile(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Небезопасная конструкция: перехват NullPointerException
    public static String unsafeGetUserName(User user) {
        try {
            return user.getProfile().getName();
        } catch (NullPointerException e) {
            return "Anonymous";
        }
    }

    // Безопасная конструкция: явная проверка на null
    public static String safeGetUserName(User user) {
        if (user != null && user.getProfile() != null) {
            return user.getProfile().getName();
        } else {
            return "Anonymous";
        }
    }

    public static void main(String[] args) {
        User userWithProfile = new User(new Profile("Alice"));
        User userWithoutProfile = new User(null);
        User nullUser = null;

        System.out.println("Небезопасно:");
        System.out.println("userWithProfile: " + unsafeGetUserName(userWithProfile));
        System.out.println("userWithoutProfile: " + unsafeGetUserName(userWithoutProfile));
        System.out.println("nullUser: " + unsafeGetUserName(nullUser));

        System.out.println("\nБезопасно:");
        System.out.println("userWithProfile: " + safeGetUserName(userWithProfile));
        System.out.println("userWithoutProfile: " + safeGetUserName(userWithoutProfile));
        System.out.println("nullUser: " + safeGetUserName(nullUser));
    }
}

