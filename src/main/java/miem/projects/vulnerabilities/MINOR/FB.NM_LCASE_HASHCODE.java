package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;
import java.util.HashSet;

public class NM_LCASE_HASHCODE {
    static class BadHashCode {
        private int id;
        private String name;
        
        // Неправильное имя метода
        public int hashcode() {  // FB.NM_LCASE_HASHCODE
            return id;
        }
    }
    
    static class GoodHashCode {
        private int id;
        private String name;
        
        // Правильное имя метода
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GoodHashCode that = (GoodHashCode) o;
            return id == that.id && Objects.equals(name, that.name);
        }
    }

    public static void main(String[] args) {
        BadHashCode bad1 = new BadHashCode();
        BadHashCode bad2 = new BadHashCode();
        
        // HashSet будет использовать Object.hashCode()
        HashSet<BadHashCode> badSet = new HashSet<>();
        badSet.add(bad1);
        System.out.println("Bad contains: " + badSet.contains(bad2));  // false
        
        GoodHashCode good1 = new GoodHashCode();
        GoodHashCode good2 = new GoodHashCode();
        
        // HashSet будет использовать переопределенный hashCode()
        HashSet<GoodHashCode> goodSet = new HashSet<>();
        goodSet.add(good1);
        System.out.println("Good contains: " + goodSet.contains(good2));  // true
    }
}