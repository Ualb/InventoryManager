package inv.mgr.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() throws Exception {
        SessionFactory sf = null;
        try{
            sf = new Configuration().configure("/inv/mgr/hibernate.cfg.xml").buildSessionFactory();
        }catch (Exception ex){
            throw new Exception("No hay conexión a internet");
        }
        return sf;
        //return new Configuration().configure("/inv/mgr/hibernate.cfg.xml").buildSessionFactory();
    }
}