package Bussinesslayer.entity.space;

import java.time.LocalDate;

public class Sprint extends Space {
    private int projectId;

    public Sprint(int id, String name, String description, boolean status, LocalDate startDate, LocalDate endDate, int projectId) {
        super(id, name, description, status, startDate, endDate);
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
