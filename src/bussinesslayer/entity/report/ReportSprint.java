package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportSprint extends Report{
    private int sprintId;

    public ReportSprint(int id, LocalTime time, LocalDate date, String description, int sprintId) {
        super(id, time, date, description);
        this.sprintId = sprintId;
    }
    public ReportSprint() {

    }

    public ReportSprint(LocalTime time, LocalDate date, String description, int sprintId) {
        super(time, date, description);
        this.sprintId = sprintId;
    }

    public ReportSprint() {

    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }
}
