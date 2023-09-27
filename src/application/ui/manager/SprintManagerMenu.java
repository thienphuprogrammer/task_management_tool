package application.ui.manager;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.sapce.sprint.ISprintService;
import bussinesslayer.service.sapce.sprint.SprintService;

import java.time.LocalDate;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class SprintManagerMenu {
    // -------------------- Properties ------------------------
    private ISprintService sprintService = new SprintService();
    private int projectId;
    public enum CHOICE_SPRINT_MANAGER_MENU {
        EXIT,
        CREATE_SPRINT,
        UPDATE_SPRINT,
        DELETE_SPRINT,
        VIEW_ALL_SPRINTS,
        INTO_TASK_MANAGER
    }
    // -------------------- Constructor ------------------------

    public SprintManagerMenu(int projectId) throws Exception {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------
    public void processMenuForSprintManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Sprint Manager Menu");
            printValueMenu("Manager\\Project\\Sprint");
            for (CHOICE_SPRINT_MANAGER_MENU choice : CHOICE_SPRINT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SPRINT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SPRINT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_SPRINT -> this.createSprint();
                        case UPDATE_SPRINT -> this.updateSprint();
                        case DELETE_SPRINT -> this.deleteSprint();
                        case VIEW_ALL_SPRINTS -> this.viewAllSprints();
                        case INTO_TASK_MANAGER -> this.processMenuForTaskManager();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    /*
     * Create Sprint
     * check validation
     * check StartDate < EndDate
     */
    private void createSprint() {
        try {
            String name = readString("Name: ");
            String description = readString("Description: ");
            LocalDate startDate = readLocalDate("Start date: ");
            LocalDate endDate = readLocalDate("End date: ");
            Sprint sprint = new Sprint(name, description, startDate, endDate, projectId);
            sprintService.create(sprint);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Update Sprint
     * check sprintId exist
     * check sprint in project
     * check StartDate < EndDate
     * check validation
     */
    private void updateSprint() {
        try {
            int sprintId = readInt("Enter sprint id: ");
            Sprint sprint = sprintService.getById(sprintId);
            if (sprint.getProjectId() == projectId) {
                sprint.setName(readString("Enter new sprint name: "));
                sprint.setDescription(readString("Enter new sprint description: "));
                sprint.setStartDate(readLocalDate("Enter new sprint start date: "));
                sprint.setEndDate(readLocalDate("Enter new sprint end date: "));
                sprintService.update(sprint);
            } else {
                printValueln("Sprint is not in this project.");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }

    }

    /*
     * Delete Sprint
     * check sprintId exist
     * check sprint in project
     * check validation
     * ask user to confirms
     * Delete all task has sprint_id of this sprint
     * Delete report of Task has sprint_id of this sprint
     */
    private void deleteSprint() {
        try {
            int sprintId = readInt("Enter sprint id: ");
            if (sprintService.getById(sprintId).getProjectId() == projectId) {
                sprintService.delete(sprintId);
            } else {
                printValueln("Sprint is not in this project.");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }

    /*
     * View All Sprint
     * list is null ?
     */
    private void viewAllSprints() {
        try {
            List<Sprint> sprintList = sprintService.getAllSprintsOfProject(projectId);
            for (Sprint sprint : sprintList) {
                printValue("id: " + sprint.getId() + " ".repeat(10 - String.valueOf(sprint.getId()).length()) + "|");
                printValue("name: " + sprint.getName() + " ".repeat(20 - String.valueOf(sprint.getName()).length()) + "|");
                printValue("start date: " + sprint.getStartDate() + " ".repeat(10 - String.valueOf(sprint.getStartDate()).length()) + "|");
                printValue("end date: " + sprint.getEndDate() + " ".repeat(10 - String.valueOf(sprint.getEndDate()).length()) + "|");
                printValueln("description: " + sprint.getDescription() + " ".repeat(30 - String.valueOf(sprint.getDescription()).length()) + "|");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }

    /*
     * Process Menu for Task Manager
     * check sprintId exist
     * check sprint in project
     */
    private void processMenuForTaskManager() throws Exception {
        int sprintId = readInt("Enter sprint id: ");
        TaskMangerMenu taskMangerMenu = new TaskMangerMenu(sprintId);
        taskMangerMenu.processMenuForTaskManager();
    }
}
