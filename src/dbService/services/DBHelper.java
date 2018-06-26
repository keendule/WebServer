package dbService.services;

import java.sql.Connection;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {

    private static SessionFactory sessionFactory;
    private static Connection connection;

    private DBHelper(){

    }

    private static void configureSessionFactory()
            throws HibernateException {

        Configuration configuration = new Configuration();
        configuration.configure("resources/hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
            configureSessionFactory();
        return sessionFactory;
    }
    
    
    public static Connection getConnection() {
        if(connection == null)
            configureConnection();
        return connection;
    }

    private static void configureConnection() {

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver() );
            connection = DriverManager.getConnection("jdbc:mysql://localhost/users_db?" +
                    "user=test&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
