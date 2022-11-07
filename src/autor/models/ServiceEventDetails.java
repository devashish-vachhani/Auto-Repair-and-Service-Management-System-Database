package models;

public class ServiceEventDetails {
    Integer serviceEventId;
    Integer serviceId;

    public ServiceEventDetails(Integer serviceEventId, Integer serviceId) {
        this.serviceEventId = serviceEventId;
        this.serviceId = serviceId;
    }

    public Integer getServiceEventId() {
        return serviceEventId;
    }

    public void setServiceEventId(Integer serviceEventId) {
        this.serviceEventId = serviceEventId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
