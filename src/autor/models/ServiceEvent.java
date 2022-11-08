package models;

public class ServiceEvent {
    Integer SeId;
    String Vin;
    Long MechId;
    Integer ScId;
    Long CustId;
    Integer AmountPaid;
    Integer AmountCharged;
    Integer ServiceDate;
    String Status;
    Integer TotalHours;

    public ServiceEvent(Integer seId, String vin, Long mechId, Integer scId, Long custId, Integer amountPaid, Integer amountCharged, Integer serviceDate, String status, Integer totalHours) {
        this.SeId = seId;
        this.Vin = vin;
        this.MechId = mechId;
        this.ScId = scId;
        this.CustId = custId;
        this.AmountPaid = amountPaid;
        this.AmountCharged = amountCharged;
        this.ServiceDate = serviceDate;
        this.Status = status;
        this.TotalHours = totalHours;
    }

    public ServiceEvent(Integer seId, String vin, Long mechId, Integer amountCharged, Integer serviceDate) {
        this.SeId = seId;
        this.Vin = vin;
        this.MechId = mechId;
        this.AmountCharged = amountCharged;
        this.ServiceDate = serviceDate;
    }

    public ServiceEvent() {

    }

    public Integer getSeId() {
        return SeId;
    }

    public void setSeId(Integer seId) {
        SeId = seId;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public Long getMechId() {
        return MechId;
    }

    public void setMechId(Long mechId) {
        MechId = mechId;
    }

    public Integer getScId() {
        return ScId;
    }

    public void setScId(Integer scId) {
        ScId = scId;
    }

    public Long getCustId() {
        return CustId;
    }

    public void setCustId(Long custId) {
        CustId = custId;
    }

    public Integer getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        AmountPaid = amountPaid;
    }

    public Integer getAmountCharged() {
        return AmountCharged;
    }

    public void setAmountCharged(Integer amountCharged) {
        AmountCharged = amountCharged;
    }

    public Integer getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Integer serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(Integer totalHours) {
        TotalHours = totalHours;
    }

}
