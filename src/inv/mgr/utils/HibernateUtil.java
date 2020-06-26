package mgr.utils;

import mgr.utils.viewsutils.Alerts;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() throws Exception {
        SessionFactory sf = null;
        try{
            sf = new Configuration().configure("/mgr/hibernate.cfg.xml").buildSessionFactory();
        }catch (Exception ex){
            throw new Exception("No hay conexión a internet");
        }
        return sf;
    }
}