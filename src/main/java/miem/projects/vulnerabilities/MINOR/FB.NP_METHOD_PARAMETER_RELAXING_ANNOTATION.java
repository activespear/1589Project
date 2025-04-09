package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FB_NP_METHOD_PARAMETER_RELAXING_ANNOTATION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: противоречивые аннотации
        class Processor {
            void process(@Nullable String input) {
                if (input != null) {
                    save(input.toLowerCase());  // Анализатор может предупредить
                }
            }
            
            void save(@Nonnull String data) {
                System.out.println("Saving: " + data);
            }
        }
        
        new Processor().process(null);
    }

    public static void correctTest() {
        // Корректно: согласованные аннотации
        class ProperProcessor {
            void process(@Nullable String input) {
                if (input == null) {
                    return;
                }
                save(input.toLowerCase());  // Без предупреждений
            }
            
            void save(@Nonnull String data) {
                System.out.println("Saving: " + data);
            }
            
            // Или явное преобразование
            void alternativeProcess(@Nullable String input) {
                save(input != null ? input.toLowerCase() : "");
            }
        }
        
        ProperProcessor processor = new ProperProcessor();
        processor.process("test");
        processor.process(null);
    }
}