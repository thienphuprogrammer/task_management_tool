package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportTask extends Report{
    private int task_id;

    public ReportTask(int id, LocalTime time, LocalDate date, String description, int task_id) {
        super(id, time, date, description);
        this.task_id = task_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
