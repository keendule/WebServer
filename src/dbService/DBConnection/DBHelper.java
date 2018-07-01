package dbService.DBConnection;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContext;



public class DBHelper {

    private static SessionFactory sessionFactory;
    private static JDBCConnectionPool jdbcConnectionPool;

    private DBHelper(){

    }

    private static void configureSessionFactory(ServletContext servletContext)
            throws HibernateException {

        Configuration configuration = new Configuration();

        try {
            configuration.configure(Paths.get(servletContext
                    .getResource("WEB-INF/resources/hibernate.cfg.xml")
                    .toURI())
                    .toFile());

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(ServletContext servletContext) {
        if(sessionFactory == null)
            configureSessionFactory(servletContext);
        return sessionFactory;
    }
    
    
    public static JDBCConnectionPool getConnectionPoll() {
        if(jdbcConnectionPool == null)
            configureConnection();
        return jdbcConnectionPool;
    }

    private static void configureConnection() {

        jdbcConnectionPool = new JDBCConnectionPool(
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/users_db?",
                "test", "");
    }


}
