package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportBacklog extends Report{
    private int backlog_id;
    private int task_id;

    public ReportBacklog(int id, LocalTime time, LocalDate date, String description, int backlog_id, int task_id) {
        super(id, time, date, description);
        this.backlog_id = backlog_id;
        this.task_id = task_id;
    }

    public int getBacklog_id() {
        return backlog_id;
    }

    public void setBacklog_id(int backlog_id) {
        this.backlog_id = backlog_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
