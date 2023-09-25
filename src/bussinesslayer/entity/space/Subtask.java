package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Subtask extends Space {
    // -------------------- Properties ------------------------
    private int memberId;
    private int taskId;
    private int status;
    // -------------------- Constructor ------------------------
    public Subtask(int id, String name, String description, LocalDate startDate, LocalDate endDate, int memberId, int taskId, int status) {
        super(id, name, description, startDate, endDate);
        this.memberId = memberId;
        this.taskId = taskId;
        this.status = status;
    }

    public Subtask(String name, String description, LocalDate startDate, LocalDate endDate, int taskId, int status) {
        super(name, description, startDate, endDate);
        this.taskId = taskId;
        this.status = status;
    }

    public Subtask() {
    }
    // -------------------- Getters and Setters ------------------------

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
