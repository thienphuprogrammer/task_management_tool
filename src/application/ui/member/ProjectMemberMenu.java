package application.ui.member;

import bussinesslayer.entity.document.Document;
import bussinesslayer.entity.space.Project;
import bussinesslayer.service.document.DocumentService;
import bussinesslayer.service.document.IDocumentService;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProjectMemberMenu {
    // -------------------- Properties ------------------------
    private final IProjectService serviceProject = new ProjectService();
    private final IDocumentService serviceDocs = new DocumentService();
    private int memberId = 1;
    public enum CHOICE_PROJECT_MEMBER_MENU {
        EXIT,
        VIEW_ALL_MY_PROJECTS,
        VIEW_ALL_DOCUMENT_OF_PROJECT,
        INTO_SPRINT_MEMBER
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
                        case VIEW_ALL_MY_PROJECTS -> this.viewAllProjectsOfMember();
                        case VIEW_ALL_DOCUMENT_OF_PROJECT -> this.viewAllDocumentsOfProject();
                        case INTO_SPRINT_MEMBER -> this.processMenuForSprintMember();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    /*
     * View all projects
     * get all project which member is assigned
     */
    private void viewAllProjectsOfMember() {
        try {
            List<Project> projects = serviceProject.getAllProjectsOfMember(memberId);
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

    /*
     * View all documents
     * check list is null ?
     * check project_id is exist
     * check project is assigned to member
     */
    private void viewAllDocumentsOfProject() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getProjectByMemberId(projectId, memberId);
            if (project != null) {
                if (project.getManagerId() == memberId) {
                    DocumentMemberMenu documentMemberMenu = new DocumentMemberMenu(projectId);
                    documentMemberMenu.processMenuForDocumentMember();
                }
            } else {
                printValueln("Project not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Process Menu for Sprint Member
     * check project is assigned to member
     * check project is exist
     */
    private void processMenuForSprintMember() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getProjectByMemberId(projectId, memberId);
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
