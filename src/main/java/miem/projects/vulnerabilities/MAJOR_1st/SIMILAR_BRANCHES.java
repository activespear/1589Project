package miem.projects.vulnerabilities.MAJOR_1st;

public class SIMILAR_BRANCHES {

    static void log(String message) {
        System.out.println(message);
    }

    static void unsafeBranch(boolean isAdmin) {
        if (isAdmin) {
            log("Access granted");
        } else {
            log("Access granted");  // Повторяющееся одинаковое действие в обеих ветках
        }
    }

    static void safeBranch(boolean isAdmin) {
        if (isAdmin) {
            log("Access granted to admin");
        } else {
            log("Access granted to user");
        }
    }

    public static void main(String[] args) {
        System.out.println("Unsafe branch with isAdmin=true:");
        unsafeBranch(true);

        System.out.println("Unsafe branch with isAdmin=false:");
        unsafeBranch(false);

        System.out.println("Safe branch with isAdmin=true:");
        safeBranch(true);

        System.out.println("Safe branch with isAdmin=false:");
        safeBranch(false);
    }
}

