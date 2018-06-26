package dbService.dao;

import entity.UserEntity;

import java.util.List;

public interface UserDAO {

    List<UserEntity> getAllUsers();

    void updateUser(UserEntity user);

    void deleteUser(int id);

    void createUser(UserEntity user);

    UserEntity getUser(int id);

}
