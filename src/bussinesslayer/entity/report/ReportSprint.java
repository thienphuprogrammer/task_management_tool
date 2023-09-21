package Bussinesslayer.entity.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportSprint extends Report{
    private int sprint_id;

    public ReportSprint(int id, LocalTime time, LocalDate date, String description, int sprint_id) {
        super(id, time, date, description);
        this.sprint_id = sprint_id;
    }

    public int getSprint_id() {
        return sprint_id;
    }

    public void setSprint_id(int sprint_id) {
        this.sprint_id = sprint_id;
    }
}
