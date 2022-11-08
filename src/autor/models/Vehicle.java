package models;

public class Vehicle {
    public String Vin;
    public Long UserId;
    public Integer ScId;
    public String Brand;
    public Long Mileage;
    public Integer Year;
    public String LastService;


    public Vehicle(String vin, Long userId, Integer scId, String brand, Long mileage, Integer year, String lastService) {
        this.Vin = vin;
        this.UserId = userId;
        this.ScId = scId;
        this.Brand = brand;
        this.Mileage = mileage;
        this.Year = year;
        this.LastService = lastService;
    }

    public Vehicle() {

    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Integer getScId() {
        return ScId;
    }

    public void setScId(Integer scId) {
        ScId = scId;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Long getMileage() {
        return Mileage;
    }

    public void setMileage(Long mileage) {
        Mileage = mileage;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public String getLastService() {
        return LastService;
    }

    public void setLastService(String lastService) {
        LastService = lastService;
    }
}

