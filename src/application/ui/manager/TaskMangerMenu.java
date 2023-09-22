package application.ui.manager;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class TaskMangerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> serviceManager;
    private IService<Task> serviceTask;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK,
        UPDATE_TASK,
        DELETE_TASK,
        VIEW_TASK,
        VIEW_TASK_PROGRESS_TRACKING,
        ASSIGN_TASK_TO_MEMBER,
        REASSIGN_TASK_TO_MEMBER,
    }
    //  ------------------- Constructor ------------------------
    public TaskMangerMenu(IService<Manager> service) {
        this.serviceManager = service;
    }

    public TaskMangerMenu(IService<Manager> serviceManager, IService<Task> serviceTask) {
        this.serviceManager = serviceManager;
        this.serviceTask = serviceTask;
    }

    //  ------------------- Getters and Setters ------------------------

    public IService<Manager> getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(IService<Manager> serviceManager) {
        this.serviceManager = serviceManager;
    }

    public IService<Task> getServiceTask() {
        return serviceTask;
    }

    public void setServiceTask(IService<Task> serviceTask) {
        this.serviceTask = serviceTask;
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
                        case ASSIGN_TASK_TO_MEMBER -> this.assignTaskToMember();
                        case REASSIGN_TASK_TO_MEMBER -> this.reassignTaskToMember();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createTask(Task task) throws Exception {
        serviceTask.create(task);
    }
    private void updateTask(Task task) throws Exception {
        serviceTask.update(task);
    }
    private void deleteTask(int taskId) throws Exception {
        serviceTask.delete(taskId);

    }
    private void viewTask() throws Exception {
        serviceTask.viewAll();
    }
    private void viewTaskProgressTracking() {

    }
    private void assignTaskToMember() {

    }
    private void reassignTaskToMember() {

    }
}
