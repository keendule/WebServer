package dbService.services;

import dbService.Exceptions.NoSuchOperationException;
import dbService.dao.UsersDAO;
import entity.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import static dbService.services.DatabaseOperations.*;

public class UserService {

    private final SessionFactory sessionFactory;

    public UserService() {
        this.sessionFactory = DBService.getSessionFactory();
    }

    public List<UsersEntity> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO usersDAO = new UsersDAO(session);

            List<UsersEntity> list = usersDAO.getAllUsers();
            session.close();

            return list;
        }catch (HibernateException e){
            e.printStackTrace();
        }


        return new ArrayList<>();
    }

    public void updateUser(UsersEntity user) {
        doDatabaseTransaction(user, UPDATE);
    }

    public UsersEntity getUser(int id){ //ToDo как быть с исключениями

        Session session = sessionFactory.openSession();
        UsersDAO usersDAO = new UsersDAO(session);


        UsersEntity user = usersDAO.getUser(id);

        session.close();

        return user;

    }

    public void deleteUser(UsersEntity user){
        doDatabaseTransaction(user, DELETE);

    }

    public void createUser(UsersEntity user) {
        doDatabaseTransaction(user, CREATE);

    }

    private void doDatabaseTransaction(UsersEntity user, DatabaseOperations databaseOperation) {//ToDo возможно ли переделать на лямбду
        try {
            Session session = sessionFactory.openSession();
            UsersDAO usersDAO = new UsersDAO(session);
            Transaction transaction = session.beginTransaction();

            switch (databaseOperation) {
                case CREATE:
                    usersDAO.createUser(user);
                    break;
                case UPDATE:
                    usersDAO.updateUser(user);
                    break;
                case DELETE:
                    usersDAO.deleteUser(user.getId());
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
