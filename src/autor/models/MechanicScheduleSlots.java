package models;

public class MechanicScheduleSlots {
    Integer Week;
    Integer SlotDay;
    Long MechId;
    Integer SlotIdMin;
    Integer SlotsMin;

    public MechanicScheduleSlots(Integer week, Integer slotDay, Long mechId, Integer slotIdMin, Integer slotsMin) {
        this.Week = week;
        this.SlotDay = slotDay;
        this.MechId = mechId;
        this.SlotIdMin = slotIdMin;
        this.SlotsMin = slotsMin;
    }

    public Integer getWeek() {
        return Week;
    }

    public void setWeek(Integer week) {
        Week = week;
    }

    public Integer getSlotDay() {
        return SlotDay;
    }

    public void setSlotDay(Integer slotDay) {
        SlotDay = slotDay;
    }

    public Long getMechId() {
        return MechId;
    }

    public void setMechId(Long mechId) {
        MechId = mechId;
    }

    public Integer getSlotIdMin() {
        return SlotIdMin;
    }

    public void setSlotIdMin(Integer slotIdMin) {
        SlotIdMin = slotIdMin;
    }

    public Integer getSlotsMin() {
        return SlotsMin;
    }

    public void setSlotsMin(Integer slotsMin) {
        SlotsMin = slotsMin;
    }
}
