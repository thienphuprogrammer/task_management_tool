package bussinesslayer.entity.space;

public class Backlog {
    private int id;
    private String title;
    private String description;
    private String fileURL;
    private int projectId;

    public Backlog(int id, String title, String description, String fileURL, int projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileURL = fileURL;
        this.projectId = projectId;
    }

    public Backlog(String title, String description, String fileURL, int projectId) {
        this.title = title;
        this.description = description;
        this.fileURL = fileURL;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
