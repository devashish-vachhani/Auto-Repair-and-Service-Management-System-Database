package models;

public class User {
    Long userid;

    Integer sc_id;
    String username;
    UserRoleEnum role;

    public Integer getSc_id() {
        return sc_id;
    }

    public void setSc_id(Integer sc_id) {
        this.sc_id = sc_id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
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

    public User(Long userid, Integer sc_id, String username, UserRoleEnum role) {
        this.username = username;
        this.role = role;
        this.userid = userid;
        this.sc_id = sc_id;
    }
}
