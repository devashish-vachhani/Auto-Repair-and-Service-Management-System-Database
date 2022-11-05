package models;

public class User {
    Integer Uid;
    UserRoleEnum Role;

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public UserRoleEnum getRole() {
        return Role;
    }

    public void setRole(UserRoleEnum role) {
        Role = role;
    }

    public User(Integer uid, UserRoleEnum role) {
        Uid = uid;
        Role = role;
    }
}
