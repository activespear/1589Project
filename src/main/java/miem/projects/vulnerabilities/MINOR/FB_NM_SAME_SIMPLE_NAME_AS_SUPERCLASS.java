package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_SAME_SIMPLE_NAME_AS_SUPERCLASS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
    package alpha;
        class Foo {
            public void doSomething() {
                System.out.println("Alpha Foo");
            }
        }

    package beta;
        class Foo extends alpha.Foo {
            @Override
            public void doSomething() {
                System.out.println("Beta Foo");
            }
        }

        System.out.println("Created classes with identical names in different packages (INSECURE)");
    }

    public static void correctTest() {
    package alpha;
        class AlphaFoo {
            public void doSomething() {
                System.out.println("Alpha Foo");
            }
        }

    package beta;
        class BetaFoo extends alpha.AlphaFoo {
            @Override
            public void doSomething() {
                System.out.println("Beta Foo");
            }
        }

        System.out.println("Created classes with unique names across packages (SECURE)");
    }
