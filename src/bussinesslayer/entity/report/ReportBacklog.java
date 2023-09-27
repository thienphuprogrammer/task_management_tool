package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportBacklog extends Report {
    private int backlogId;

    public ReportBacklog(LocalTime time, LocalDate date, String description, int backlog_id) {
        super(time, date, description);
        this.backlogId = backlog_id;
    }

    public ReportBacklog() {

    }

    public int getBacklogId() {
        return backlogId;
    }

    public void setBacklogId(int backlogId) {
        this.backlogId = backlogId;
    }

}
