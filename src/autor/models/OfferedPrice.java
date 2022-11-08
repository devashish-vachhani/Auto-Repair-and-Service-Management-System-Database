package models;

public class OfferedPrice {
    Integer ServiceCenterId;
    Integer ServiceId;
    String Brand;
    Integer Price;


    public OfferedPrice(Integer serviceCenterId, Integer serviceId, String brand, Integer price) {
        this.ServiceCenterId = serviceCenterId;
        this.ServiceId = serviceId;
        this.Brand = brand;
        this.Price = price;
    }

    public OfferedPrice() {

    }

    public Integer getServiceCenterId() {
        return ServiceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        ServiceCenterId = serviceCenterId;
    }

    public Integer getServiceId() {
        return ServiceId;
    }

    public void setServiceId(Integer serviceId) {
        ServiceId = serviceId;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}