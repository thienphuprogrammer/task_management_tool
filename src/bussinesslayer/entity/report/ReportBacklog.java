package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportBacklog extends Report {
    private int backlog_id;

    public ReportBacklog(LocalTime time, LocalDate date, String description, int backlog_id) {
        super(time, date, description);
        this.backlog_id = backlog_id;
    }

    public ReportBacklog() {

    }

    public int getBacklog_id() {
        return backlog_id;
    }

    public void setBacklog_id(int backlog_id) {
        this.backlog_id = backlog_id;
    }

}
