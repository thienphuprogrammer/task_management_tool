package bussinesslayer.entity.space;

public class Backlog {
    private int id;
    private String title;
    private String description;
    private String fileURL;

    public Backlog(int id, String title, String description, String fileURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileURL = fileURL;
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
}
