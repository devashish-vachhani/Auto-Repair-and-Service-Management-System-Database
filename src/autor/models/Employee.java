package models;

public class Employee {
    String username;
    EmployeeRoleEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EmployeeRoleEnum getRole() {
        return role;
    }

    public void setRole(EmployeeRoleEnum role) {
        this.role = role;
    }

    public Employee(String username, EmployeeRoleEnum role) {
        this.username = username;
        this.role = role;
    }
}
