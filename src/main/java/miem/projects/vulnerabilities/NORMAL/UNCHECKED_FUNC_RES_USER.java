package miem.projects.vulnerabilities.NORMAL;

public class UNCHECKED_FUNC_RES_USER {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String username = getUsernameById(-1);
        // Не проверили на null — и сразу вызываем метод (NullPointerException)
        System.out.println("Username length: " + username.length());
    }

    public static void correctTest() {
        String username = getUsernameById(-1);
        if (username != null) {
            System.out.println("Username length: " + username.length());
        } else {
            System.out.println("User not found");
        }
    }

    public static String getUsernameById(int id) {
        if (id < 0) {
            return null;
        }
        return "user_" + id;
    }
}