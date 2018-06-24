package dbService.dao;

import entity.UsersEntity;
import org.hibernate.Session;

import java.util.*;


public class UsersDAO {
    private final Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

        public List<UsersEntity> getAllUsers() {
           return session.createCriteria(UsersEntity.class).list();
    }


    public void updateUser(UsersEntity user) {
        session.update(user);
    }

    public UsersEntity getUser(int id){
        return (UsersEntity) session.get(UsersEntity.class,id);
    }

    public void deleteUser(int id){
        UsersEntity user = getUser(id);
        session.delete(user);
    }

    public void createUser(UsersEntity user) {
        session.save(user);
    }
}
