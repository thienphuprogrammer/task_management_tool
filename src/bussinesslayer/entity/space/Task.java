package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Task extends Space {
    public enum TASK_STATUS {
        OPEN,
        IN_PROGRESS,
        COMPLETED,
        ON_HOLD,
        CANCELLED
    }

    // -------------------- Properties ------------------------
    private int memberId = -1;
    private int sprintId = -1;
    private int backlogId;
    private int status = TASK_STATUS.OPEN.ordinal();
    // -------------------- Constructor ------------------------
    public Task(String name, String description, LocalDate startDate, LocalDate endDate, int sprintId) {
        super(name, description, startDate, endDate);
        this.sprintId = sprintId;
    }

    public Task(int id, String name, String description, LocalDate startDate, LocalDate endDate, int memberId, int sprintId) {
        super(id, name, description, startDate, endDate);
        this.memberId = memberId;
        this.sprintId = sprintId;
    }

    public Task(String name, String description, LocalDate startDate, LocalDate endDate, int memberId, int sprintId) {
        super(name, description, startDate, endDate);
        this.memberId = memberId;
        this.sprintId = sprintId;
    }

    public Task(String name, String description, LocalDate startDate, LocalDate endDate) {
        super(name, description, startDate, endDate);
    }

    public Task() {
    }
    // -------------------- Getters and Setters ------------------------

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getStatus() {
        return TASK_STATUS.values()[status].ordinal();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBacklogId() {
        return backlogId;
    }

    public void setBacklogId(int backlogId) {
        this.backlogId = backlogId;
    }
}
