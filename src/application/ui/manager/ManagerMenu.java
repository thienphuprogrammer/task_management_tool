package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.manager.IManagerService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ManagerMenu {
    public enum CHOICE_MANAGER_MENU {
        EXIT,
        // Project
        PROJECT_MANAGER,
        //Profile
        PROFILE_MANAGER,
        VIEW_ALL_MEMBER
    }
    // -------------------- Properties ------------------------
    IManagerService serviceManager;
    private int managerId;

    // -------------------- Constructor ------------------------
    public ManagerMenu(IManagerService service) {
        this.serviceManager = service;
    }
    public void processMenuForManager() {
        boolean exit = false;

        while (!exit) {
            printLineSeparate("Manager Menu");
            printValueMenu("\\Manager");
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
                        case PROFILE_MANAGER -> this.manageProfile();
                        case VIEW_ALL_MEMBER -> this.viewAllMember();
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
        ProjectManagerMenu managerProjectManagerMenu = new ProjectManagerMenu(managerId);
        managerProjectManagerMenu.processMenuForProjectManager();

    }
    private void manageProfile() throws Exception {
        ProfileManagerMenu profileManagerMenu = new ProfileManagerMenu(managerId);
        profileManagerMenu.processMenuForProfileManager();
    }
    private void viewAllMember() throws Exception {
        serviceManager.viewAllMember(managerId);
    }
}
