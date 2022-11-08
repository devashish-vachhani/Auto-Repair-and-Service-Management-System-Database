package models;

public class ServiceHistory {
    Integer SId;
    String Vin;
    TypeEnum Type;
    Integer Price;
    String Name;
    Integer ServiceDate;

    public ServiceHistory(Integer SId, String vin, TypeEnum type, Integer price, String name, Integer serviceDate) {
        this.SId = SId;
        this.Vin = vin;
        this.Type = type;
        this.Price = price;
        this.Name = name;
        this.ServiceDate = serviceDate;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public TypeEnum getType() {
        return Type;
    }

    public void setType(TypeEnum type) {
        Type = type;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Integer serviceDate) {
        ServiceDate = serviceDate;
    }
}
