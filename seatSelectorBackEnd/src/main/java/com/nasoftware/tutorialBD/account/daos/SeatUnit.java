package com.nasoftware.tutorialBD.account.daos;

public class SeatUnit {
    int id;
    int seat;
    String name;
    private long start_time;
    private long end_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStart_time() { return start_time; }

    public void setStart_time(long start_time) { this.start_time = start_time; }

    public long getEnd_time() { return end_time; }

    public void setEnd_time(long end_time) { this.end_time = end_time; }

}
