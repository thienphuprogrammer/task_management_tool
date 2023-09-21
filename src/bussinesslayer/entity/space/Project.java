package Bussinesslayer.entity.space;

import java.time.LocalDate;

public class Project extends Space {
    private int managerId;

    public Project(int id, String name, String description, LocalDate startDate, LocalDate endDate, int managerId) {
        super(id, name, description, startDate, endDate);
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
