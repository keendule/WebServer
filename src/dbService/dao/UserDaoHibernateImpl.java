package dbService.dao;

import dbService.DBConnection.DBHelper;
import dbService.services.DatabaseOperations;
import entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;

import static dbService.services.DatabaseOperations.*;



import java.util.*;


public class UserDaoHibernateImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl(ServletContext servletContext) {
        this.sessionFactory = DBHelper.getSessionFactory(servletContext);
    }

    public List<UserEntity> getAllUsers() {
           Session session = sessionFactory.openSession();
           return session.createCriteria(UserEntity.class).list();
    }


    public void updateUser(UserEntity user) {
        doDatabaseTransaction(user,UPDATE);
    }

    public UserEntity getUser(int id){
        Session session = sessionFactory.openSession();
        return (UserEntity) session.get(UserEntity.class,id);
    }

    public void deleteUser(int id){
        UserEntity user = getUser(id);
        doDatabaseTransaction(user,DELETE);
    }

    public void createUser(UserEntity user) {
        doDatabaseTransaction(user,CREATE);
    }

    private void doDatabaseTransaction(UserEntity user, DatabaseOperations databaseOperation) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            switch (databaseOperation) {
                case CREATE:
                    session.save(user);
                    break;
                case UPDATE:
                    session.update(user);
                    break;
                case DELETE:
                    session.delete(user);
                    break;
                default:
                    throw new IllegalArgumentException("No such operation implemented");
            }

            transaction.commit();
            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }
}

