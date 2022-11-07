package models;

public class MechanicSchedule {
    public Integer msId;
    public Integer mechId;
    public Integer scId;
    public Integer slotId;
    public boolean availablility;
    public boolean working;
    public MechanicSchedule(Integer msId, Integer mechId, Integer scId, Integer slotId, boolean availablility,
                            boolean working) {
        this.msId = msId;
        this.mechId = mechId;
        this.scId = scId;
        this.slotId = slotId;
        this.availablility = availablility;
        this.working = working;
    }
    public Integer getMsId() {
        return msId;
    }
    public void setMsId(Integer msId) {
        this.msId = msId;
    }
    public Integer getMechId() {
        return mechId;
    }
    public void setMechId(Integer mechId) {
        this.mechId = mechId;
    }
    public Integer getScId() {
        return scId;
    }
    public void setScId(Integer scId) {
        this.scId = scId;
    }
    public Integer getSlotId() {
        return slotId;
    }
    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }
    public boolean isAvailablility() {
        return availablility;
    }
    public void setAvailablility(boolean availablility) {
        this.availablility = availablility;
    }
    public boolean isWorking() {
        return working;
    }
    public void setWorking(boolean working) {
        this.working = working;
    }
}
