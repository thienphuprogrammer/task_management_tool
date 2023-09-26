package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Project extends Space {
    // -------------------- Properties ------------------------
    private int managerId;
    // -------------------- Constructor ------------------------
    public Project(int id, String name, String description, LocalDate startDate, LocalDate endDate, int managerId) {
        super(id, name, description, startDate, endDate);
        this.managerId = managerId;
    }

    public Project() {
    }

    public Project(int id, String name, String description, LocalDate startDate, LocalDate endDate) {
        super(id, name, description, startDate, endDate);
    }

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, int managerId) {
        super(name, description, startDate, endDate);
        this.managerId = managerId;
    }
    // -------------------- Getters and Setters ------------------------

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
