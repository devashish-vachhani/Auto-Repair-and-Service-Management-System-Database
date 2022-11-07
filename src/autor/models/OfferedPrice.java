package models;

public class OfferedPrice {
    Integer serviceCenterId;
    Integer serviceId;
    BrandEnum brand;
    Integer price;

    public OfferedPrice(Integer serviceCenterId, Integer serviceId, BrandEnum brand, Integer price) {
        this.serviceCenterId = serviceCenterId;
        this.serviceId = serviceId;
        this.brand = brand;
        this.price = price;
    }


    public Integer getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public BrandEnum getBrand() {
        return brand;
    }

    public void setBrand(BrandEnum brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
