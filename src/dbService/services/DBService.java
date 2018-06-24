package dbService.services;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBService  {

    private static final SessionFactory sessionFactory = configureSessionFactory();

    private DBService(){

    }

    private static SessionFactory configureSessionFactory()
            throws HibernateException {

        Configuration configuration = new Configuration();
        configuration.configure("resources/hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }



}
