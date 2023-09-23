package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SprintManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_SPRINT_MANAGER_MENU {
        EXIT,
        CREATE_SPRINT,
        UPDATE_SPRINT,
        DELETE_SPRINT,
        VIEW_SPRINT
    }
    // -------------------- Constructor ------------------------

    public SprintManagerMenu(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Getters and Setters ------------------------
    public IService<Manager> getService() {
        return service;
    }

    public void setService(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Methods ------------------------
    public void processMenuForSprintManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Sprint Manager Menu");
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
                        case VIEW_SPRINT -> this.viewSprint();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createSprint() {

    }
    private void updateSprint() {

    }
    private void deleteSprint() {

    }
    private void viewSprint() {

    }
}
