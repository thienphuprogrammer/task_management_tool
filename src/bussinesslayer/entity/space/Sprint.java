package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Sprint extends Space {
    private int projectId;

    public Sprint(int id, String name, String description, LocalDate startDate, LocalDate endDate, int projectId) {
        super(id, name, description, startDate, endDate);
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
