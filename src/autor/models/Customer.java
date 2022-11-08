package models;

public class Customer {
    public Integer ScId;
    public Long UserId;
    public String FName;
    public String LName;
    public String Address;
    public String Email;
    public Long PhoneNo;
    public Boolean Standing;
    public Boolean Status;

    public Customer(Integer scId, Long userId, String FName, String LName, String address, String email, Long phoneNo, Boolean standing, Boolean status) {
        this.ScId = scId;
        this.UserId = userId;
        this.FName = FName;
        this.LName = LName;
        this.Address = address;
        this.Email = email;
        this.PhoneNo = phoneNo;
        this.Standing = standing;
        this.Status = status;
    }

    public Customer(Long userId, String FName, String LName, String address, String email, Long phoneNo) {
        this.UserId = userId;
        this.FName = FName;
        this.LName = LName;
        this.Address = address;
        this.Email = email;
        this.PhoneNo = phoneNo;
    }

    public Integer getScId() {
        return ScId;
    }

    public void setScId(Integer scId) {
        ScId = scId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        PhoneNo = phoneNo;
    }

    public Boolean getStanding() {
        return Standing;
    }

    public void setStanding(Boolean standing) {
        Standing = standing;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}


