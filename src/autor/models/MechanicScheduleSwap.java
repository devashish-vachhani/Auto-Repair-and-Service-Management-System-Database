package models;

public class MechanicScheduleSwap {
    public Integer registerId;
    public Integer msId1;
    public Integer msId2;
    public boolean approved;

    public MechanicScheduleSwap(Integer registerId, Integer msId1, Integer msId2, boolean approved) {
        this.registerId = registerId;
        this.msId1 = msId1;
        this.msId2 = msId2;
        this.approved = approved;
    }

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getMsId1() {
        return msId1;
    }

    public void setMsId1(Integer msId1) {
        this.msId1 = msId1;
    }

    public Integer getMsId2() {
        return msId2;
    }

    public void setMsId2(Integer msId2) {
        this.msId2 = msId2;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
