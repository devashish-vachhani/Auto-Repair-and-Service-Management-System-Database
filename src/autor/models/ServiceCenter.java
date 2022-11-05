package models;

public class ServiceCenter {
    String address;
    String tel;
    String state;
    Boolean openOnSaturdays;
    Integer hourlyRate;

    public ServiceCenter(String address, String tel, String state, Boolean openOnSaturdays, Integer hourlyRate) {
        this.address = address;
        this.tel = tel;
        this.state = state;
        this.openOnSaturdays = openOnSaturdays;
        this.hourlyRate = hourlyRate;
    }
}
