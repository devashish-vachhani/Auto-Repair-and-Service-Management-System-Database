package models;

public class Vehicle {
    public String Vin;
    public String CarManufacturer;
    public Long CurrentMileage;
    public String LastScheduledMaintenanceService;
    public Integer Year;
    public Integer CustomerId;

    public Vehicle(String Vin, String CarManufacturer, Long CurrentMileage, String LastScheduledMaintenanceService, Integer Year, Integer CustomerId)
    {
        this.Vin = Vin;
        this.CarManufacturer = CarManufacturer;
        this.CurrentMileage = CurrentMileage;
        this.LastScheduledMaintenanceService = LastScheduledMaintenanceService;
        this.Year = Year;
        this.CustomerId = CustomerId;
    }
    public String getVin() { return this.Vin; }
    public String getCarManufacturer() { return this.CarManufacturer; }
    public Long getCurrentMileage() { return this.CurrentMileage; }
    public String getLastScheduledMaintenanceService() { return this.LastScheduledMaintenanceService; }
    public Integer getYear() { return this.Year; }
    public Integer getCustomerId() { return this.CustomerId; }

    public void setVin( String Vin) {  this.Vin =  Vin; }
    public void setCarManufacturer(String CarManufacturer) {  this.CarManufacturer =  CarManufacturer;}
    public void setCurrentMileage(Long CurrentMileage) {  this.CurrentMileage = CurrentMileage; }
    public void setLastScheduledMaintenanceService(String LastScheduledMaintenanceService) { this.LastScheduledMaintenanceService = LastScheduledMaintenanceService; }
    public void setYear(Integer Year) { this.Year = Year; }
    public void setCustomerId(Integer CustomerId) { this.CustomerId = CustomerId; }
}

