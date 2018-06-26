package dbService.services;

import dbService.dao.UserDAO;
import dbService.dao.UserDAOFactory;
import dbService.dao.UserDaoHibernateImpl;
import entity.UserEntity;

import javax.servlet.ServletContext;
import java.util.List;


public class UserService {



    private final UserDAO userDAO;

    public UserService(ServletContext servletContext){
        userDAO = new UserDAOFactory(servletContext).create();
    }

    public List<UserEntity> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(UserEntity user) {
        userDAO.updateUser(user);
    }

    public UserEntity getUser(int id) {
        return userDAO.getUser(id);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);

    }

    public void createUser(UserEntity user) {
        userDAO.createUser(user);
    }
}


