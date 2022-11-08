package models;

public class MaintenanceService {
    Integer serviceId;
    String Bundle;

    public MaintenanceService(Integer serviceId, String bundle) {
        this.serviceId = serviceId;
        Bundle = bundle;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getBundle() {
        return Bundle;
    }

    public void setBundle(String bundle) {
        Bundle = bundle;
    }
}
