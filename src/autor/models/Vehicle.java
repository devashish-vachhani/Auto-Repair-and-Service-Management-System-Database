package models;

public class Vehicle {
    public Integer Vin;
    public String CarManufacturer;
    public Long CurrentMileage;
    public Character LastScheduledMaintenanceService;
    public Integer Year;

    public Vehicle(Integer Vin, String CarManufacturer, Long CurrentMileage, Character LastScheduledMaintenanceService, Integer Year)
    {
        this.Vin = Vin;
        this.CarManufacturer = CarManufacturer;
        this.CurrentMileage = CurrentMileage;
        this.LastScheduledMaintenanceService = LastScheduledMaintenanceService;
        this.Year = Year;
    }
    public Integer getVin() { return this.Vin; }
    public String getCarManufacturer() { return this.CarManufacturer; }
    public Long getCurrentMileage() { return this.CurrentMileage; }
    public Character getLastScheduledMaintenanceService() { return this.LastScheduledMaintenanceService; }
    public Integer getYear() { return this.Year; }

    public void setVin( Integer Vin) {  this.Vin =  Vin; }
    public void setCarManufacturer(String CarManufacturer) {  this.CarManufacturer =  CarManufacturer;}
    public void setCurrentMileage(Long CurrentMileage) {  this.CurrentMileage = CurrentMileage; }
    public void setLastScheduledMaintenanceService(Character LastScheduledMaintenanceService) { this.LastScheduledMaintenanceService = LastScheduledMaintenanceService; }
    public void setYear(Integer Year) { this.Year = Year; }
}

