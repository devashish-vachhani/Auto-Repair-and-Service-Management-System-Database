package models;

public class Customer {
    public Integer CustomerID;
    public String FullName;
    public Integer ServiceCenter;
    public Boolean Status;
    public Boolean Standing;

    public Customer(Integer CustomerID, String FullName, Integer ServiceCenter, Boolean Status, Boolean Standing)
    {
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.ServiceCenter = ServiceCenter;
        this.Status = Status;
        this.Standing = Standing;
    }
    public Integer getCustomerID() { return this.CustomerID; }
    public String getFullName() { return this.FullName; }
    public Integer getServiceCenter() { return this.ServiceCenter; }
    public Boolean getStatus() { return this.Status; }
    public Boolean getStanding() { return this.Standing; }

    public void setCustomerID( Integer CustomerID) {  this.CustomerID =  CustomerID;}
    public void setServiceCenter(Integer ServiceCenter) {  this.ServiceCenter =  ServiceCenter;}
    public void setFullName(String FullName) {  this.FullName = FullName; }
    public void setStatus(Boolean Status) { this.Status = Status; }
    public void setStanding(Boolean Standing) { this.Standing = Standing; }
}