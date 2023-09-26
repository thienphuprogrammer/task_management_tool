package application.ui.member;

import bussinesslayer.entity.Document;
import bussinesslayer.entity.space.Project;
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
    private int memberId = 1;
    public enum CHOICE_PROJECT_MEMBER_MENU {
        EXIT,
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
    private void viewAllProject() {
        try {
            List<Project> projects = serviceProject.getAllProjectMember(memberId);
            for (Project project : projects) {
                printLineSeparate("Project");
                printValue("id: " + project.getId() + " ".repeat(10 - String.valueOf(project.getId()).length()) + "|");
                printValue("Description: " + project.getDescription() + " ".repeat(40 - String.valueOf(project.getDescription()).length()) + "|");
                printValue("Start date: " + project.getStartDate() + " ".repeat(20 - String.valueOf(project.getStartDate()).length()) + "|");
                printValue("End date: " + project.getEndDate() + " ".repeat(20 - String.valueOf(project.getEndDate()).length()) + "|");
                printValueln("Manager id: " + project.getManagerId() + " ".repeat(10 - String.valueOf(project.getManagerId()).length()) + "|");
                printLineSeparate("");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void viewDocument() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getMemberByProjectId(projectId, memberId);
            if (project != null) {
                Document doc = serviceDocs.getDocument(projectId);
                printLineSeparate("Document");
                printValue("id: " + doc.getId() + " ".repeat(10 - String.valueOf(doc.getId()).length()) + "|");
                printValue("Title: " + doc.getTitle() + " ".repeat(20 - String.valueOf(doc.getTitle()).length()) + "|");
                printValueln("Description: " + doc.getDescription() + " ".repeat(40 - String.valueOf(doc.getDescription()).length()) + "|");
                printValueln("Content: " + doc.getContent());
                printLineSeparate("");
            } else {
                printValueln("Project not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void processMenuForSprintMember() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getMemberByProjectId(projectId, memberId);
            if (project != null && project.getManagerId() == memberId) {
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
