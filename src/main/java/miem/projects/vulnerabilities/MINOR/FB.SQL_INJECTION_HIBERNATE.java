package miem.projects.vulnerabilities.MINOR.FB;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class FB_SQL_INJECTION_HIBERNATE {

    private final SessionFactory sessionFactory;

    public FB_SQL_INJECTION_HIBERNATE(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    public void getUserUnsafe(String input) {
        Session session = sessionFactory.openSession();
        try {
            Query q = session.createQuery("select t from UserEntity t where id = " + input);
            q.list(); // выполнение
        } finally {
            session.close();
        }
    }

    
    public void getUserSafe(String input) {
        Session session = sessionFactory.openSession();
        try {
            Query q = session.createQuery("select t from UserEntity t where id = :userId");
            q.setString("userId", input);
            q.list();
        } finally {
            session.close();
        }
    }

    
    public void getUserSafeCriteria(String input) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(UserEntity.class)
                    .add(Restrictions.eq("id", input));
            criteria.list();
        } finally {
            session.close();
        }
    }
}
