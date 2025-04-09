package miem.projects.vulnerabilities.MINOR.FB;

public class FB_IS2_INCONSISTENT_SYNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная реализация с неконсистентной синхронизацией
    public static class BankAccountIncorrect {
        private int balance;
        private final Object lock = new Object();

        public int getBalance() {
            return balance;  // Не синхронизировано!
        }

        public void deposit(int amount) {
            synchronized (lock) {
                balance += amount;
            }
        }

        public void withdraw(int amount) {
            synchronized (lock) {
                if (balance >= amount) {
                    balance -= amount;
                }
            }
        }
    }

    // Корректная реализация с полной синхронизацией
    public static class BankAccountCorrect {
        private int balance;
        private final Object lock = new Object();

        public int getBalance() {
            synchronized (lock) {
                return balance;
            }
        }

        public void deposit(int amount) {
            synchronized (lock) {
                balance += amount;
            }
        }

        public void withdraw(int amount) {
            synchronized (lock) {
                if (balance >= amount) {
                    balance -= amount;
                }
            }
        }
    }

    // Лучшая практика с использованием java.util.concurrent
    public static class BankAccountBest {
        private final Object lock = new Object();
        private volatile int balance;  // volatile для видимости

        public int getBalance() {
            synchronized (lock) {
                return balance;
            }
        }

        public void deposit(int amount) {
            synchronized (lock) {
                balance += amount;
            }
        }

        public void withdraw(int amount) {
            synchronized (lock) {
                if (balance >= amount) {
                    balance -= amount;
                }
            }
        }
    }

    public static void incorrectTest() {
        BankAccountIncorrect account = new BankAccountIncorrect();
        account.deposit(1000);
        
        new Thread(() -> {
            // Может прочитать устаревшее значение баланса
            System.out.println("Incorrect balance: " + account.getBalance());
        }).start();
    }

    public static void correctTest() {
        BankAccountCorrect account = new BankAccountCorrect();
        account.deposit(1000);
        
        new Thread(() -> {
            System.out.println("Correct balance: " + account.getBalance());
        }).start();
    }
}