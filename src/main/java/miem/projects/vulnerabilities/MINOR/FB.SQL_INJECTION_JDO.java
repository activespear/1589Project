package miem.projects.vulnerabilities.MINOR.FB;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

public class FB_SQL_INJECTION_JDO {

    private final PersistenceManager pm;

    public FB_SQL_INJECTION_JDO(PersistenceManager pm) {
        this.pm = pm;
    }

    // ❌ Потенциально небезопасное
    public List getUserUnsafe(String userInput) {
        Query query = pm.newQuery("SELECT FROM User WHERE name == '" + userInput + "'");
        return (List) query.execute();
    }

    // Корректная конструкция (с параметрами)
    public List getUserSafe(String userInput) {
        Query query = pm.newQuery("SELECT FROM User WHERE name == :name");
        query.declareParameters("String name");
        return (List) query.execute(userInput);
    }
}
