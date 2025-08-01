package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HE_EQUALS_NO_HASHCODE {

    // Небезопасная реализация: equals без hashCode
    static class UserUnsafe {
        private String username;

        public UserUnsafe(String username) {
            this.username = username;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof UserUnsafe u) {
                return Objects.equals(this.username, u.username);
            }
            return false;
        }
        // hashCode отсутствует!
    }

    // Безопасная реализация: equals и hashCode
    static class UserSafe {
        private String username;

        public UserSafe(String username) {
            this.username = username;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof UserSafe u) {
                return Objects.equals(this.username, u.username);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(username);
        }
    }

    public static void runUnsafe() {
        Set<UserUnsafe> set = new HashSet<>();
        UserUnsafe u1 = new UserUnsafe("admin");
        UserUnsafe u2 = new UserUnsafe("admin");

        set.add(u1);
        System.out.println("Unsafe contains u2? " + set.contains(u2)); // Может вернуть false!
    }

    public static void runSafe() {
        Set<UserSafe> set = new HashSet<>();
        UserSafe u1 = new UserSafe("admin");
        UserSafe u2 = new UserSafe("admin");

        set.add(u1);
        System.out.println("Safe contains u2? " + set.contains(u2)); // Вернет true
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

