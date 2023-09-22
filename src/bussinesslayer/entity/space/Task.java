package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Task extends Space {
    private int memberId;
    private int sprintId;
    private int status;

    public Task(int id, String name, String description, LocalDate startDate, LocalDate endDate, int memberId, int sprintId, int status) {
        super(id, name, description, startDate, endDate);
        this.memberId = memberId;
        this.sprintId = sprintId;
        this.status = status;
    }

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

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
