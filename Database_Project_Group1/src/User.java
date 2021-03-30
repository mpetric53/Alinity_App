import java.sql.*;

public class User {

    public int userId;
    public String username;
    public String password;
    public String birthday;
    public String email;

    public User(int userId, String username, String password, String birthday, String email){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
