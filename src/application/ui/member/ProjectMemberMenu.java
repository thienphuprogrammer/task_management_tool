package application.ui.member;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.DocsService;
import bussinesslayer.service.IDocsService;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProjectMemberMenu {
    // -------------------- Properties ------------------------
    private final IProjectService serviceProject = new ProjectService();
    private final IDocsService serviceDocs = new DocsService();
    private int memberId;
    public enum CHOICE_PROJECT_MEMBER_MENU {
        EXIT,
        VIEW_MY_PROJECT,
        VIEW_ALL_MY_PROJECT,
        VIEW_DOCUMENT,
        SPRINT_MEMBER
    }
    // -------------------- Constructor ------------------------

    public ProjectMemberMenu(int memberId) throws Exception {
        this.memberId = memberId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForProjectMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Project Member Menu");
            printValueMenu("Member\\Project");
            for (CHOICE_PROJECT_MEMBER_MENU choice : CHOICE_PROJECT_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_PROJECT_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROJECT_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_MY_PROJECT -> this.viewAProject();
                        case VIEW_ALL_MY_PROJECT -> this.viewAllProject();
                        case VIEW_DOCUMENT -> this.viewDocument();
                        case SPRINT_MEMBER -> this.processMenuForSprintMember();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewAProject() {
        try {
            int projectId = readInt("Enter project id: ");
            serviceProject.getProject(projectId, memberId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewAllProject() {
        try {
            int projectId = readInt("Enter project id: ");
            List<Member>  list = serviceProject.getAllMember(projectId, memberId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewDocument() throws Exception {
        int projectId = readInt("Enter project id: ");
        serviceDocs.getDocument(projectId, memberId);
    }
    private void processMenuForSprintMember() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getProject(projectId, memberId);
            if (project != null) {
                SprintMemberMenu sprintMemberMenu = new SprintMemberMenu(projectId, memberId);
                sprintMemberMenu.processMenuForSprintMember();
            } else {
                printValueln("Project not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
