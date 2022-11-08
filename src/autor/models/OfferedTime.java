package models;

public class OfferedTime {
    Integer serviceId;
    BrandEnum brand;
    Integer hrs;

    public OfferedTime(Integer serviceId, BrandEnum brand, Integer hrs) {
        this.serviceId = serviceId;
        this.brand = brand;
        this.hrs = hrs;
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

    public Integer getHrs() {
        return hrs;
    }

    public void setHrs(Integer hrs) {
        this.hrs = hrs;
    }
}
