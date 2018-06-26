package dbService.dao;

import dbService.Executors.Executor;
import dbService.services.DBHelper;
import entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {

    Connection connection = DBHelper.getConnection();

    @Override
    public List<UserEntity> getAllUsers() {
        Executor executor = new Executor(connection);
        List<UserEntity> users;
        try {
            users = executor.execCreateStatementQuery("Select * From users",
                    x -> {
                        List<UserEntity> list = new ArrayList<>();
                        try {
                            while (x.next()) {

                                UserEntity user = new UserEntity.Builder()
                                        .setId(x.getInt("id"))
                                        .setLogin(x.getString("login"))
                                        .setPassword(x.getString("password"))
                                        .setName(x.getString("name")).build();

                                list.add(user);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return list;
                    });
        return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(UserEntity user) {
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("UPDATE users SET " +
                   "login = ?, password = ?, name = ? WHERE id = ?",
                    user.getLogin(),user.getPassword(),user.getName(),user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("DELETE FROM users WHERE id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(UserEntity user) {
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("INSERT INTO users " +
                            "(login,password,name) VALUES (?,?,?)",
                    user.getLogin(),user.getPassword(),user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserEntity getUser(int id) {
        Executor executor = new Executor(connection);
        try {
           return executor.execSelectPreparedStatementQuery("Select * FROM users" +
                    " WHERE id = ? ",x -> {
                UserEntity user = null;
                try {

                    while (x.next()) {

                         user = new UserEntity.Builder()
                                .setId(x.getInt("id"))
                                .setLogin(x.getString("login"))
                                .setPassword(x.getString("password"))
                                .setName(x.getString("name")).build();

                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

                return user;
            },id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
