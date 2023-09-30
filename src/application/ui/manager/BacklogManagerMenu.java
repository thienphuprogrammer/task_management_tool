package application.ui.manager;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.sapce.backog.BacklogService;
import bussinesslayer.service.sapce.backog.IBacklogService;

import java.time.LocalDate;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    // -------------------- Properties ------------------------
    private final IBacklogService serviceBacklog = new BacklogService();
    private int projectId;
    private int backlogId;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_TASK_IN_BACKLOG,
        UPDATE_TASK_IN_BACKLOG,
        DELETE_TASK_IN_BACKLOG,
        VIEW_ALL_TASK_IN_BACKLOG,
        ADD_TASK_IN_BACKLOG_TO_SPRINT
    }
    // -------------------- Constructor ------------------------
    public BacklogManagerMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------

    private void checkBacklogNone() throws Exception {
        if (serviceBacklog.getAll().size() == 0) {
            printValueln("Backlog is empty.");
            serviceBacklog.create(new Backlog(projectId));
            backlogId = serviceBacklog.getAll().get(0).getId();
        }
    }

    public void processMenuForBacklogManager() throws Exception {
        boolean exit = false;
        checkBacklogNone();

        while (!exit) {
            printLineSeparate("Backlog Manager Menu");
            printValueMenu("Manager\\Project\\Backlog");
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
                        case CREATE_TASK_IN_BACKLOG -> this.createTaskInBacklog();
                        case UPDATE_TASK_IN_BACKLOG -> this.updateTaskInBacklog();
                        case DELETE_TASK_IN_BACKLOG -> this.deleteTaskInBacklog();
                        case VIEW_ALL_TASK_IN_BACKLOG -> this.viewAllTaskInBacklog();
                        case ADD_TASK_IN_BACKLOG_TO_SPRINT -> this.addTaskInBacklogToSprint();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    // Create Task in Backlog and set sprint_id = -1 and member_id = -1
    // Check sprint_id and member_id
    private void createTaskInBacklog()  {
        try {
            String name = readString("Name: ");
            String description = readString("Description: ");
            LocalDate startDate = readLocalDate("Start date: ");
            LocalDate endDate = readLocalDate("End date: ");
            int memberId = -1;
            int sprintId = -1;
            Task task = new Task(name, description, startDate, endDate, memberId, sprintId, backlogId);
            serviceBacklog.createTaskInBacklog(task);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    // Update Task
    // Check task_id must be in this backlog
    private void updateTaskInBacklog() {
        try {
            int taskId = readInt("Task ID: ");
            Task task = serviceBacklog.getTaskInBacklogByTaskId(taskId);

            if (task.getBacklogId() == backlogId) {
                task.setName(readString("Name: "));
                task.setDescription(readString("Description: "));
                task.setStartDate(readStartDate("Start date: "));
                task.setEndDate(readEndDate("End date: ", task.getStartDate()));
                serviceBacklog.updateTaskInBacklog(task);
            } else {
                printValueln("Task is not in this backlog.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Delete Task
     * Check task_id must be in this backlog
     * set backlog_id in task = -1
     */
    private void deleteTaskInBacklog() {
        try {
            int taskId = readInt("Task ID: ");
            Task task = serviceBacklog.getTaskInBacklogByTaskId(taskId);

            if (task.getBacklogId() == backlogId) { // Check task_id
                if (task.getSprintId() == -1) { // Check sprint_id
                    serviceBacklog.deleteTaskInBacklog(taskId);
                } else {
                    printValueln("Task is in sprint.");
                }
            } else {
                printValueln("Task is not in this backlog.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    // View All Task in Backlog
    private void viewAllTaskInBacklog() {
        try {
            List<Task> list = serviceBacklog.getAllTasksInBacklog(backlogId);
            if (list.isEmpty()) {
                printValueln("There is no task in this backlog.");
            } else {
                for (Task task : list) {
                    printLineSeparate();
                    printValue(("Task ID: " + task.getId() + " ".repeat(10 - String.valueOf(task.getId()).length()) + "|"));
                    printValue("Name: " + task.getName() + " ".repeat(20 - String.valueOf(task.getName()).length()) + "|");
                    printValue("Description: " + task.getDescription() + " ".repeat(40 - String.valueOf(task.getDescription()).length()) + "|");
                    printValue("Start date: " + task.getStartDate() + " ".repeat(20 - String.valueOf(task.getStartDate()).length()) + "|");
                    printValue("End date: " + task.getEndDate() + " ".repeat(20 - String.valueOf(task.getEndDate()).length()) + "|");
                    printValue("Status: " + task.getStatus() + " ".repeat(10 - String.valueOf(task.getStatus()).length()) + "|");
                    printValue("Member ID: " + task.getMemberId() + " ".repeat(10 - String.valueOf(task.getMemberId()).length()) + "|");
                    printValue("Sprint ID: " + task.getSprintId() + " ".repeat(10 - String.valueOf(task.getSprintId()).length()) + "|");
                    printLineSeparate();
                }
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Add Task in Backlog
     * Check sprint_id and task_id
     * Check task_id must be in this backlog
     * Check sprint_id must be in this project
     */
    private void addTaskInBacklogToSprint() {
        try {
            int taskId = readInt("Task ID: ");
            Task task = serviceBacklog.getTaskInBacklogByTaskId(taskId);
            if (task.getBacklogId() == backlogId) {
                int sprintId = readInt("Sprint ID: ");
                Sprint sprint = serviceBacklog.getSprintBySprintId(sprintId);
                if (sprint.getProjectId() == projectId) {
                    serviceBacklog.addTaskInBacklogToSprint(backlogId, taskId, sprintId);
                } else {
                    printValueln("Sprint is not in this project.");
                }
            } else {
                printValueln("Task is not in this backlog.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
