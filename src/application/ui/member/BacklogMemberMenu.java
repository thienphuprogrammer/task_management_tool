package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.report.reportbacklog.IReportBacklogService;
import bussinesslayer.service.report.reportbacklog.ReportBacklogService;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class BacklogMemberMenu {
    // -------------------- Properties ------------------------
    private final IBacklogService serviceBacklog = new BacklogService();
    private final IReportBacklogService serviceReportBacklog = new ReportBacklogService();
    private int projectId;
    public enum CHOICE_BACKLOG_MEMBER_MENU {
        EXIT,
        VIEW_BACKLOG,
        VIEW_REPORT_BACKLOG,
    }
    // -------------------- Constructor ------------------------
    public BacklogMemberMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------
    public void processMenuForBacklogMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Backlog Member Menu");
            printValueMenu("Member\\Project\\Backlog");
            for (CHOICE_BACKLOG_MEMBER_MENU choice : CHOICE_BACKLOG_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_BACKLOG_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_BACKLOG_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_BACKLOG -> this.viewBacklog();
                        case VIEW_REPORT_BACKLOG -> this.viewReportBacklog();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    private void viewBacklog() throws Exception {
        serviceBacklog.getBacklogByProjectId(projectId);
    }
    private void viewReportBacklog() {
        serviceReportBacklog.viewReport(projectId);
    }
}
