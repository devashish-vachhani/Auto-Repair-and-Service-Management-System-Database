package models;

public class Customer {
    public Integer CustomerId;
    public String FullName;
    public Integer ServiceCenter;
    public Boolean Status;
    public Boolean Standing;

    public Customer(Integer CustomerId, String FullName, Integer ServiceCenter, Boolean Status, Boolean Standing)
    {
        this.CustomerId = CustomerId;
        this.FullName = FullName;
        this.ServiceCenter = ServiceCenter;
        this.Status = Status;
        this.Standing = Standing;
    }
    public Integer getCustomerID() { return this.CustomerId; }
    public String getFullName() { return this.FullName; }
    public Integer getServiceCenter() { return this.ServiceCenter; }
    public Boolean getStatus() { return this.Status; }
    public Boolean getStanding() { return this.Standing; }

    public void setCustomerID( Integer CustomerId) {  this.CustomerId =  CustomerId;}
    public void setServiceCenter(Integer ServiceCenter) {  this.ServiceCenter =  ServiceCenter;}
    public void setFullName(String FullName) {  this.FullName = FullName; }
    public void setStatus(Boolean Status) { this.Status = Status; }
    public void setStanding(Boolean Standing) { this.Standing = Standing; }
}