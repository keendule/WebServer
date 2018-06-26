package dbService.dao;

import dbService.DBConnection.JDBCConnectionPool;
import dbService.Executors.Executor;
import dbService.DBConnection.DBHelper;
import entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {

    private JDBCConnectionPool jdbcConnectionPool = DBHelper.getConnectionPoll();

    @Override
    public List<UserEntity> getAllUsers() {
        Connection connection = jdbcConnectionPool.takeOut();
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
        }finally {
            jdbcConnectionPool.takeIn(connection);
        }
        return null;
    }

    @Override
    public void updateUser(UserEntity user) {
        Connection connection = jdbcConnectionPool.takeOut();
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("UPDATE users SET " +
                   "login = ?, password = ?, name = ? WHERE id = ?",
                    user.getLogin(),user.getPassword(),user.getName(),user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcConnectionPool.takeIn(connection);
        }
    }

    @Override
    public void deleteUser(int id) {
        Connection connection = jdbcConnectionPool.takeOut();
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("DELETE FROM users WHERE id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcConnectionPool.takeIn(connection);
        }
    }

    @Override
    public void createUser(UserEntity user) {
        Connection connection = jdbcConnectionPool.takeOut();
        Executor executor = new Executor(connection);
        try {
            executor.execUpdatePreparedStatementQuery("INSERT INTO users " +
                            "(login,password,name) VALUES (?,?,?)",
                    user.getLogin(),user.getPassword(),user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcConnectionPool.takeIn(connection);
        }
    }

    @Override
    public UserEntity getUser(int id) {
        Connection connection = jdbcConnectionPool.takeOut();
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
        }finally {
            jdbcConnectionPool.takeIn(connection);
        }

        return null;
    }
}
