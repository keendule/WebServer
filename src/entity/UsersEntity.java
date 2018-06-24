package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "users_db")
public class UsersEntity {
    private int id;
    private String name;
    private String password;
    private String login;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, login);
    }

    public UsersEntity(){

    }

    public UsersEntity(int id){
        this.id = id;
    }

    public UsersEntity(int id, String name, String login,String password) {

        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public UsersEntity(String name, String login, String password) {

        this.name = name;
        this.login = login;
        this.password = password;
    }
}
