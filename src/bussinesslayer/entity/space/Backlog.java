package bussinesslayer.entity.space;

public class Backlog {
    private int id;
    private int projectId;

    public Backlog(int id, int projectId) {
        this.id = id;
        this.projectId = projectId;
    }

    public Backlog(int projectId) {
        this.projectId = projectId;
    }

    public Backlog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
