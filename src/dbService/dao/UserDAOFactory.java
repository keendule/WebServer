package dbService.dao;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Properties;

public class UserDAOFactory {
    private String SQL;

    public UserDAOFactory(ServletContext servletContext){
        Properties appProp = new Properties();
        try {
            appProp.load(servletContext.getResourceAsStream("WEB-INF/app.properties"));
            SQL = ((String)appProp.get("SQL")).toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public UserDAO create(){
        switch (SQL){
            case "jdbc" : return new UserDaoJDBCImpl();
            case "hibernate" : return new UserDaoHibernateImpl();
            default: return null;
        }
    }

}
