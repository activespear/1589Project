package miem.projects.vulnerabilities.CRITICAL;

public class AUTHENTICATE_IN_LOOP {
    public void authenticateUserLoop(int[] userIds) {
        for (int userId : userIds) {
            if (!authenticate(userId)) {
                System.out.println("Authentication failed for user: " + userId);
            }
        }
    }

    public void authenticateUserBatch(int[] userIds) {
        boolean allAuthenticated = true;
        for (int userId : userIds) {
            if (!authenticate(userId)) {
                allAuthenticated = false;
                break;
            }
        }
        if (allAuthenticated) {
            System.out.println("All users authenticated successfully.");
        } else {
            System.out.println("Some users failed authentication.");
        }
    }

    public boolean authenticate(int userId) {
        // В реальной реализации здесь была бы проверка аутентификации
        return true;
    }

    public static void main(String[] args) {
        AUTHENTICATE_IN_LOOP runner = new AUTHENTICATE_IN_LOOP();
        int[] testUserIds = {1, 2, 3, 4, 5};

        System.out.println("Running authenticateUserLoop (safe version):");
        runner.authenticateUserLoop(testUserIds);

        System.out.println("\nRunning authenticateUserBatch (unsafe version):");
        runner.authenticateUserBatch(testUserIds);
    }
}

