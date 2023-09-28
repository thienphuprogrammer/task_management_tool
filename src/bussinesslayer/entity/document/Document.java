package bussinesslayer.entity.document;

public class Document {
    // -------------------- Properties ------------------------
    private int id;
    private String title;
    private String description;
    private String content;
    private int projectId;
    // -------------------- Constructor ------------------------
    public Document(String title, String description, String content, int projectId) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.projectId = projectId;
    }

    public Document() {
    }

    // -------------------- Getters and Setters ------------------------
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
