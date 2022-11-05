package models;

public class User {
    Integer userid;
    String username;
    UserRoleEnum role;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public User(Integer userid, String username, UserRoleEnum role) {
        this.username = username;
        this.role = role;
        this.userid = userid;
    }
}
