package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProjectMemberMenu {
    // -------------------- Properties ------------------------
    private final IProjectService serviceProject = new ProjectService();
    private int memberId;
    public enum CHOICE_PROJECT_MEMBER_MENU {
        EXIT,
        VIEW_SPRINT,
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
                        case VIEW_SPRINT -> this.viewSprint();
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
    private void viewSprint() {

    }
    private void viewDocument() {

    }
    private void processMenuForSprintMember() throws Exception {
        SprintMemberMenu sprintMemberMenu = new SprintMemberMenu(memberId);
        sprintMemberMenu.processMenuForSprintMember();
    }
}
