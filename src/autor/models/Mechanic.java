package models;

public class Mechanic {
    Integer userId;
    Integer serviceCenterId;
    Integer hrsWorked;

    public Mechanic(Integer userId, Integer serviceCenterId, Integer hrsWorked) {
        this.userId = userId;
        this.serviceCenterId = serviceCenterId;
        this.hrsWorked = hrsWorked;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHrsWorked() {
        return hrsWorked;
    }

    public void setHrsWorked(Integer hrsWorked) {
        this.hrsWorked = hrsWorked;
    }

    public Integer getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }
}
