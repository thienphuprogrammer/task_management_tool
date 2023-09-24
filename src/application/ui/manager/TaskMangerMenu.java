package application.ui.manager;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.report.reporttask.IReportTaskService;
import bussinesslayer.service.report.reporttask.ReportTaskService;
import bussinesslayer.service.sapce.task.ITaskService;
import bussinesslayer.service.sapce.task.TaskService;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.ManagerService;

import java.time.LocalDate;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class TaskMangerMenu {
    // -------------------- Properties ------------------------
    private final ITaskService serviceTask = new TaskService();
    private final IReportTaskService reportTaskService = new ReportTaskService();
    private int sprintId;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK,
        UPDATE_TASK,
        DELETE_TASK,
        VIEW_TASK,
        VIEW_TASK_PROGRESS_TRACKING,
        ASSIGN_TASK_TO_MEMBER,
        REASSIGN_TASK_TO_MEMBER,
        CREATE_REPORT,
        VIEW_REPORT,
        SUBTASK_MANAGER
    }
    //  ------------------- Constructor ------------------------


    public TaskMangerMenu(int sprintId) throws Exception {
        this.sprintId = sprintId;
    }

    //  ------------------- Getters and Setters ------------------------

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
                        case CREATE_REPORT -> this.createReport();
                        case VIEW_REPORT -> this.viewReport();
                        case SUBTASK_MANAGER -> this.processMenuForSubtaskManager();
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
        int taskId = readInt("Enter task id: ");
        Task task = serviceTask.getById(taskId);
        printValue(new String[]{
                String.valueOf(task.getId()),
                task.getName(),
                task.getDescription(),
                task.getStartDate().toString(),
                task.getEndDate().toString(),
                String.valueOf(task.getSprintId()),
        });
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
    private void createReport() {

    }
    private void viewReport() {

    }
    private void processMenuForSubtaskManager() throws Exception {
        int taskId = readInt("Enter task id: ");
        SubtaskManagerMenu subtaskManagerMenu = new SubtaskManagerMenu(taskId);
        subtaskManagerMenu.processMenuForSubtaskManager();
    }
}
