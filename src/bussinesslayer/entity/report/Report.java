package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;
public abstract class Report {
    protected int id;
    protected LocalTime time;
    protected LocalDate date;
    protected String description;
    public Report(int id, LocalTime time, LocalDate date, String description){
        this.id = id;
        this.time = time;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
