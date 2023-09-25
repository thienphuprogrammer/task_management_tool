package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportSubtask extends Report {
    // -------------------- Properties ------------------------
    private int subtaskId;
    // -------------------- Constructor ------------------------
    public ReportSubtask() {
    }

    public ReportSubtask(LocalTime time, LocalDate date, String description, int subtaskId) {
        super(time, date, description);
        this.subtaskId = subtaskId;
    }
    // -------------------- Getters and Setters ------------------------

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }
}
