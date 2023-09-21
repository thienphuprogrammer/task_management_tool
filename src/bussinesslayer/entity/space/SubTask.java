package Bussinesslayer.entity.space;

import java.time.LocalDate;

public class SubTask extends Space {
    private int memberId;
    private int taskId;
    private int status;

    public SubTask(int id, String name, String description, LocalDate startDate, LocalDate endDate, int memberId, int taskId, int status) {
        super(id, name, description, startDate, endDate);
        this.memberId = memberId;
        this.taskId = taskId;
        this.status = status;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
