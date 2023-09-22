package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class TaskMangerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK,
        UPDATE_TASK,
        DELETE_TASK,
        VIEW_TASK,
        VIEW_TASK_PROGRESS_TRACKING,
    }
    //  ------------------- Constructor ------------------------
    public TaskMangerMenu(IService<Manager> service) {
        this.service = service;
    }
    //  ------------------- Getters and Setters ------------------------

    public IService<Manager> getService() {
        return service;
    }

    public void setService(IService<Manager> service) {
        this.service = service;
    }
    //  ------------------- Methods ------------------------
    public void processMenuForTaskManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Task Manager Menu");
            for (CHOICE_BACKLOG_MANAGER_MENU choice : CHOICE_BACKLOG_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_BACKLOG_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_BACKLOG_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_TASK -> this.createTask();
                        case UPDATE_TASK -> this.updateTask();
                        case DELETE_TASK -> this.deleteTask();
                        case VIEW_TASK -> this.viewTask();
                        case VIEW_TASK_PROGRESS_TRACKING -> this.viewTaskProgressTracking();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createTask() {

    }
    private void updateTask() {

    }
    private void deleteTask() {

    }
    private void viewTask() {

    }
    private void viewTaskProgressTracking() {

    }
}
