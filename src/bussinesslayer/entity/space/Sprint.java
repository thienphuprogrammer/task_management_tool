package bussinesslayer.entity.space;

import java.time.LocalDate;

public class Sprint extends Space {
    private int projectId;

    public Sprint(int id, String name, LocalDate startDate, LocalDate endDate, int projectId) {
        super(id, name, startDate, endDate);
        this.projectId = projectId;
    }

    public Sprint(String name, LocalDate startDate, LocalDate endDate, int projectId) {
        super(name, startDate, endDate);
        this.projectId = projectId;
    }

    public Sprint() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
