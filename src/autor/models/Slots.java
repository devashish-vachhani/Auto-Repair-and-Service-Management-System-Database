package models;

public class Slots {
    Integer Week;
    Integer SlotDay;
    Integer Slots;
    Integer SlotId;

    public Slots(Integer week, Integer slotDay, Integer slots, Integer slotId) {
        this.Week = week;
        this.SlotDay = slotDay;
        this.Slots = slots;
        this.SlotId = slotId;
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

    public Integer getSlots() {
        return Slots;
    }

    public void setSlots(Integer slots) {
        Slots = slots;
    }

    public Integer getSlotId() {
        return SlotId;
    }

    public void setSlotId(Integer slotId) {
        SlotId = slotId;
    }
}
