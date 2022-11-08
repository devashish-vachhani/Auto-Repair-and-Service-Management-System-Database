package models;

public class AvailableMechanicsAndSlots {
    Integer slot_id;
    Integer week;
    Integer day;
    Integer slots;

    public AvailableMechanicsAndSlots(Integer slot_id, Integer week, Integer day, Integer slots) {
        this.slot_id = slot_id;
        this.week = week;
        this.day = day;
        this.slots = slots;
    }

    public Integer getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(Integer slot_id) {
        this.slot_id = slot_id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }
}
