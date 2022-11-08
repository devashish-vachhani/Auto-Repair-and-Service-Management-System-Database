package models;

public class AvailableSwapRequests {
    Long registerId;
    String mechName;
    Long slotId;

    public AvailableSwapRequests(Long registerId, String mechName, Long slotId) {
        this.registerId = registerId;
        this.mechName = mechName;
        this.slotId = slotId;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getMechName() {
        return mechName;
    }

    public void setMechName(String mechName) {
        this.mechName = mechName;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }
}
