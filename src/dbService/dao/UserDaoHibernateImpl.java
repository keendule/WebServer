package dbService.dao;

import dbService.Exceptions.NoSuchOperationException;
import dbService.services.DBHelper;
import dbService.services.DatabaseOperations;
import entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static dbService.services.DatabaseOperations.*;



import java.util.*;


public class UserDaoHibernateImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = DBHelper.getSessionFactory();
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
                    throw new NoSuchOperationException();
            }

            transaction.commit();
            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
