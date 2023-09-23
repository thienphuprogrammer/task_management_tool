package application.ui.manager;

import bussinesslayer.entity.Doc;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ManagerMenu {
    public enum CHOICE_MANAGER_MENU {
        EXIT,

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

    private void manageProject() throws Exception {
        ProjectManagerMenu managerProjectManagerMenu = new ProjectManagerMenu(service);
        managerProjectManagerMenu.processMenuForProjectManager();

    }
    private void manageBacklog() throws Exception {
        BacklogManagerMenu backlogManagerMenu = new BacklogManagerMenu(service);
        backlogManagerMenu.processMenuForBacklogManager();
    }
    private void manageSprint() throws Exception {
        SprintManagerMenu sprintManagerMenu = new SprintManagerMenu(service);
        sprintManagerMenu.processMenuForSprintManager();
    }
    private void manageTask() throws Exception {
        TaskMangerMenu taskMangerMenu = new TaskMangerMenu(service);
        taskMangerMenu.processMenuForTaskManager();
    }
    private void manageSubtask() throws Exception {
        SubtaskManagerMenu subtaskManagerMenu = new SubtaskManagerMenu(service);
        subtaskManagerMenu.processMenuForSubtaskManager();
    }
    private void manageDocument() throws Exception {
        DocumentManagerMenu documentManagerMenu = new DocumentManagerMenu(service);
        documentManagerMenu.processMenuForDocumentManager();
    }
    private void manageProfile() throws Exception {
        ProfileManagerMenu profileManagerMenu = new ProfileManagerMenu(service);
        profileManagerMenu.processMenuForProfileManager();
    }
}
