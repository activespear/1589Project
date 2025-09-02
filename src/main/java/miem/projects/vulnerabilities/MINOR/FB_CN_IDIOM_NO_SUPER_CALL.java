package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CN_IDIOM_NO_SUPER_CALL {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class User implements Cloneable {
            public String name;

            @Override
            public Object clone() {
                User user = new User();
                user.name = this.name;
                return user; // Нет вызова super.clone()
            }
        }

        User original = new User();
        original.name = "Alice";
        User cloned = (User) original.clone();
        System.out.println("Cloned user name (INSECURE): " + cloned.name);
    }

    public static void correctTest() {
        class User implements Cloneable {
            public String name;

            @Override
            public Object clone() {
                try {
                    return super.clone(); // Корректный вызов родительского метода
                } catch (CloneNotSupportedException e) {
                    throw new AssertionError(); // Не должно случаться
                }
            }
        }

        User original = new User();
        original.name = "Alice";
        User cloned = (User) original.clone();
        System.out.println("Cloned user name (SECURE): " + cloned.name);
    }
}
