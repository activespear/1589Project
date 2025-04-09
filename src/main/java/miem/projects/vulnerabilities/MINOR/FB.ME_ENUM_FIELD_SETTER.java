package miem.projects.vulnerabilities.MINOR.FB;

public class FB_ME_ENUM_FIELD_SETTER {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: enum с изменяемым полем и сеттером
        enum Status {
            ACTIVE("A"), INACTIVE("I");
            
            private String code;
            
            Status(String code) {
                this.code = code;
            }
            
            public void setCode(String code) {  // Опасный сеттер
                this.code = code;
            }
            
            public String getCode() {
                return code;
            }
        }
        
        Status.ACTIVE.setCode("X");  // Изменение enum!
        System.out.println("Modified enum: " + Status.ACTIVE.getCode());
    }

    public static void correctTest() {
        // Корректно: неизменяемый enum
        enum Status {
            ACTIVE("A"), INACTIVE("I");
            
            private final String code;  // final поле
            
            Status(String code) {
                this.code = code;
            }
            
            public String getCode() {
                return code;
            }
        }
        
        System.out.println("Proper enum: " + Status.ACTIVE.getCode());
        
        // Альтернатива для сложных случаев
        enum AdvancedStatus {
            ACTIVE {
                @Override
                public String getDisplayName() {
                    return "Active";
                }
            },
            INACTIVE {
                @Override
                public String getDisplayName() {
                    return "Inactive";
                }
            };
            
            public abstract String getDisplayName();
        }
    }
}