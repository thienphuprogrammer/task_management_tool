package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportSubtask extends Report {
    private int subtaskId;
    public ReportSubtask(int id, LocalTime time, LocalDate date, String description) {
        super(id, time, date, description);
    }

    public ReportSubtask() {
    }

    public ReportSubtask(int id, LocalTime time, LocalDate date, String description, int subtaskId) {
        super(id, time, date, description);
        this.subtaskId = subtaskId;
    }

    public ReportSubtask(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }
}
