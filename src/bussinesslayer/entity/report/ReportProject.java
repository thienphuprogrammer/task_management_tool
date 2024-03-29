package bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportProject extends Report {
    private int project_id;

    public ReportProject(String description, int project_id) {
        super(description);
        this.project_id = project_id;
    }

    public ReportProject() {
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
