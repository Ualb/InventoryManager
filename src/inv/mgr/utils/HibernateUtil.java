package inv.mgr.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        return new Configuration().configure("/inv/mgr/hibernate.cfg.xml").buildSessionFactory();
    }
}