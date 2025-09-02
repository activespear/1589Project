package miem.projects.vulnerabilities.MINOR.FB;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FB_SQL_INJECTION_JPA {

    private final EntityManager entityManager;

    public FB_SQL_INJECTION_JPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getUsersUnsafe(String email) {
        String queryStr = "SELECT u FROM User u WHERE u.email = '" + email + "'";
        Query query = entityManager.createQuery(queryStr);
        return query.getResultList();
    }

    public List<User> getUsersSafe(String email) {
        String queryStr = "SELECT u FROM User u WHERE u.email = :email";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("email", email);
        return query.getResultList();
    }
}
