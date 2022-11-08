package models;

public class RepairService {
    Integer serviceId;
    String Category;

    public RepairService(Integer serviceId, String category) {
        this.serviceId = serviceId;
        Category = category;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
