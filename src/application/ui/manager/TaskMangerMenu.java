package application.ui.manager;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;
import bussinesslayer.service.sapce.TaskService;
import bussinesslayer.service.user.ManagerService;

import java.time.LocalDate;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class TaskMangerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> serviceManager = new ManagerService();
    private IService<Task> serviceTask = new TaskService();
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
    public TaskMangerMenu(IService<Manager> service) throws Exception {
        this.serviceManager = service;
    }

    public TaskMangerMenu(IService<Manager> serviceManager, IService<Task> serviceTask) throws Exception {
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
    private void createTask() throws Exception {
        String name = readString("Enter task name: ");
        String description = readString("Enter task description: ");
        LocalDate startDate = readLocalDate("Enter task start date: ");
        LocalDate endDate = readLocalDate("Enter task end date: ");
        int memberId = readInt("Enter task member id: ");
        int sprintId = readInt("Enter task sprint id: ");
        Task task = new Task(name, description, startDate, endDate, sprintId);
        serviceTask.create(task);
    }
    private void updateTask() throws Exception {
        int taskId = readInt("Enter task id: ");
        Task task = serviceTask.getById(taskId);
        task.setName(readString("Enter task name: "));
        task.setDescription(readString("Enter task description: "));
        task.setStartDate(readLocalDate("Enter task start date: "));
        task.setEndDate(readLocalDate("Enter task end date: "));
        task.setSprintId(readInt("Enter task sprint id: "));
        serviceTask.update(task);
    }
    private void deleteTask() throws Exception {
        int taskId = readInt("Enter task id: ");
        serviceTask.delete(taskId);
    }
    private void viewTask() throws Exception {
        serviceTask.viewAll();
    }
    private void viewTaskProgressTracking() throws Exception {
        List<Task> list = serviceTask.getAll();
        for (Task task : list) {
            printValue(new String[]{
                    String.valueOf(task.getId()),
                    Task.TASK_STATUS.values()[task.getStatus()].name().replace("_", " ").toUpperCase(),
            });
        }
    }
    private void assignTaskToMember() throws Exception {
        int taskId = readInt("Enter task id: ");
        Task task = serviceTask.getById(taskId);
        int memberId = readInt("Enter member id: ");
        task.setMemberId(memberId);
        serviceTask.update(task);
    }
    private void reassignTaskToMember() throws Exception {
        int taskId = readInt("Enter task id: ");
        Task task = serviceTask.getById(taskId);
        int newMemberId = readInt("Enter new member id: ");
        task.setMemberId(newMemberId);
        serviceTask.update(task);
    }
}
