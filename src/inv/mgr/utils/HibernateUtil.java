package inv.mgr.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class HibernateUtil {

    SessionFactory sessionFactory = null;

    private HibernateUtil() {}

    private static final HibernateUtil instance = new HibernateUtil();

    public static HibernateUtil getInstance() {
        return instance;
    }

    public void buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure("resources/hibernate.cfg.xml");
        configuration.setProperty("hibernate.current_session_context_class", "thread");

        //StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory();
        /*try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }*/
    }

    public void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();

        ThreadLocalSessionContext.bind(session);
    }

    public void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);

        if (session!=null) {
            session.close();
        }
    }

    public void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
