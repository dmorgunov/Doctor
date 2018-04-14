package com.company;

import java.util.Objects;

public class Appointment {
    private Integer day;
    private Integer visitots;
    private String comment;

    public Appointment(int day, int visitots, String comment) {
        this.day = day;
        this.visitots = visitots;
        this.comment = comment;
    }

    public Appointment(){}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getVisitots() {
        return visitots;
    }

    public void setVisitots(int visitots) {
        this.visitots = visitots;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return day == that.day &&
                visitots == that.visitots &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "day=" + day +
                ", visitots=" + visitots +
                ", comment='" + comment + '\'' +
                '}';
    }
}
