package models;

public class Manager {
    Integer userId;
    Integer serviceCenterId;
    Integer annualSalary;

    public Manager(Integer userId, Integer serviceCenterId, Integer annualSalary) {
        this.userId = userId;
        this.serviceCenterId = serviceCenterId;
        this.annualSalary = annualSalary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }
}
