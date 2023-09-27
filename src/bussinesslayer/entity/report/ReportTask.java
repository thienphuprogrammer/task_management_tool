package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportTask extends Report{
    // -------------------- Properties ------------------------
    private int task_id;
    // -------------------- Constructor ------------------------
    public ReportTask(int id, LocalTime time, LocalDate date, String description, int task_id) {
        super(id, description);
        this.task_id = task_id;
    }

    public ReportTask(LocalTime time, LocalDate date, String description, int task_id) {
        super(description);
        this.task_id = task_id;
    }

    public ReportTask() {

    }
    // -------------------- Getter and Setter ------------------
    public int getTaskId() {
        return task_id;
    }

    public void setTaskId(int task_id) {
        this.task_id = task_id;
    }
}
