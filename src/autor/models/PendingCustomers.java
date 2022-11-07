package models;

import java.util.Date;

public class PendingCustomers {
    Long cust_id;
    String custFirstName;
    String custLastName;
    Integer se_id;
    String serviceDate;
    Integer amountCharged;

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public Integer getSe_id() {
        return se_id;
    }

    public void setSe_id(Integer se_id) {
        this.se_id = se_id;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Integer getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(Integer amountCharged) {
        this.amountCharged = amountCharged;
    }

    public PendingCustomers(Long cust_id, String custFirstName, String custLastName, Integer se_id, String serviceDate, Integer amountCharged) {
        this.cust_id = cust_id;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.se_id = se_id;
        this.serviceDate = serviceDate;
        this.amountCharged = amountCharged;
    }
}
