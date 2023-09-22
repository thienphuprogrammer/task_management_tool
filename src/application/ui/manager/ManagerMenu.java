package application.ui.manager;

import bussinesslayer.entity.Doc;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ManagerMenu {
    public enum CHOICE_MANAGER_MENU {
        EXIT,
        //Member
        MEMBER_MANAGER,

        // Project
        PROJECT_MANAGER,
        // Backlog
        BACKLOG_MANAGER,

        // Sprint
        SPRINT_MANAGER,

        // Task
        TASK_MANAGER,

        // Subtask
        SUBTASK_MANAGER,

        // Document
        DOCUMENT_MANAGER,
        //Profile
        PROFILE_MANAGER
    }
    // -------------------- Properties ------------------------
    IService<Manager> service;

    // -------------------- Constructor ------------------------
    public ManagerMenu(IService<Manager> service) {
        this.service = service;
    }
    public void processMenuForManager() {
        boolean exit = false;

        while (!exit) {
            printLineSeparate("Manager Menu");
            for (CHOICE_MANAGER_MENU choice : CHOICE_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");

                if (choice < 0 || choice >= CHOICE_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case MEMBER_MANAGER -> this.manageMember();
                        case PROJECT_MANAGER -> this.manageProject();
                        case BACKLOG_MANAGER -> this.manageBacklog();
                        case SPRINT_MANAGER -> this.manageSprint();
                        case TASK_MANAGER -> this.manageTask();
                        case SUBTASK_MANAGER -> this.manageSubtask();
                        case DOCUMENT_MANAGER -> this.manageDocument();
                        case PROFILE_MANAGER -> this.manageProfile();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    private void manageMember() {
        MemberManagerMenu memberManagerMenu = new MemberManagerMenu(service);
        memberManagerMenu.processMenuForMemberManager();
    }
    private void manageProject() {
        ProjectManagerMenu managerProjectManagerMenu = new ProjectManagerMenu(service);
        managerProjectManagerMenu.processMenuForProjectManager();

    }
    private void manageBacklog() {
        BacklogManagerMenu backlogManagerMenu = new BacklogManagerMenu(service);
        backlogManagerMenu.processMenuForBacklogManager();
    }
    private void manageSprint() {
        SprintManagerMenu sprintManagerMenu = new SprintManagerMenu(service);
        sprintManagerMenu.processMenuForSprintManager();
    }
    private void manageTask() {
        TaskMangerMenu taskMangerMenu = new TaskMangerMenu(service);
        taskMangerMenu.processMenuForTaskManager();
    }
    private void manageSubtask() {
        SubtaskManagerMenu subtaskManagerMenu = new SubtaskManagerMenu(service);
        subtaskManagerMenu.processMenuForSubtaskManager();
    }
    private void manageDocument() {
        DocumentManagerMenu documentManagerMenu = new DocumentManagerMenu(service);
        documentManagerMenu.processMenuForDocumentManager();
    }
    private void manageProfile() {
        ProjectManagerMenu managerProjectManagerMenu = new ProjectManagerMenu(service);
        managerProjectManagerMenu.processMenuForProjectManager();
    }
}
