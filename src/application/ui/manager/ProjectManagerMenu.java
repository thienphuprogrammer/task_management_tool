package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProjectManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_PROJECT_MANAGER_MENU {
        EXIT,
        EDIT_PROJECT,
        DELETE_PROJECT,
        CREATE_PROJECT,
        VIEW_PROJECT,
    }
    // -------------------- Constructor ------------------------

    public ProjectManagerMenu(IService<Manager> service) {
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
    public void processMenuForProjectManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Project Manager Menu");
            for (CHOICE_PROJECT_MANAGER_MENU choice : CHOICE_PROJECT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_PROJECT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROJECT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case EDIT_PROJECT -> this.editProject();
                        case DELETE_PROJECT -> this.deleteProject();
                        case CREATE_PROJECT -> this.createProject();
                        case VIEW_PROJECT -> this.viewProject();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void editProject() {

    }
    private void deleteProject() {

    }
    private void createProject() {

    }
    private void viewProject() {

    }
}
