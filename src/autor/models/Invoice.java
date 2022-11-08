package models;

public class Invoice {
    Integer SeId;
    Long CustId;
    String Vin;
    Integer ServiceDate;
    Integer SId;
    TypeEnum Type;
    String Status;
    String Name;
    Integer Price;
    Integer AmountCharged;

    public Invoice(Integer seId, Long custId, String vin, Integer serviceDate, Integer SId, TypeEnum type, String status, String name, Integer price, Integer amountCharged) {
        this.SeId = seId;
        this.CustId = custId;
        this.Vin = vin;
        this.ServiceDate = serviceDate;
        this.SId = SId;
        this.Type = type;
        this.Status = status;
        this.Name = name;
        this.Price = price;
        this.AmountCharged = amountCharged;
    }

    public Integer getSeId() {
        return SeId;
    }

    public void setSeId(Integer seId) {
        SeId = seId;
    }

    public Long getCustId() {
        return CustId;
    }

    public void setCustId(Long custId) {
        CustId = custId;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public Integer getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Integer serviceDate) {
        ServiceDate = serviceDate;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public TypeEnum getType() {
        return Type;
    }

    public void setType(TypeEnum type) {
        Type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getAmountCharged() {
        return AmountCharged;
    }

    public void setAmountCharged(Integer amountCharged) {
        AmountCharged = amountCharged;
    }
}
