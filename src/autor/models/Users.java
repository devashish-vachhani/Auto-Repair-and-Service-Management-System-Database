package models;

public class Users {
    Long UserId;
    Integer ScId;
    String Username;
    String Password;
    UserRoleEnum Role;

    public Users(Long userId, Integer scId, String username, String password, UserRoleEnum role) {
        this.UserId = userId;
        this.ScId = scId;
        this.Username = username;
        this.Password = password;
        this.Role = role;
    }

    public Users(long userId, int scId, UserRoleEnum role) {
        this.UserId = userId;
        this.ScId = scId;
        this.Role = role;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Integer getScId() {
        return ScId;
    }

    public void setScId(Integer scId) {
        ScId = scId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRoleEnum getRole() {
        return Role;
    }

    public void setRole(UserRoleEnum role) {
        Role = role;
    }
}
