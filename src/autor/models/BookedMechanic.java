package models;

public class BookedMechanic {
    Integer week;
    Integer day;
    Integer slots;

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

    public BookedMechanic(Integer week, Integer day, Integer slots) {
        this.week = week;
        this.day = day;
        this.slots = slots;
    }
}
