package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProfileManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_PROFILE_MANAGER_MENU {
        EXIT,
        UPDATE_PROFILE,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------


    public ProfileManagerMenu(IService<Manager> service) {
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

    public void processMenuForProfileManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Profile Manager Menu");
            for (CHOICE_PROFILE_MANAGER_MENU choice : CHOICE_PROFILE_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");

                if (choice < 0 || choice >= CHOICE_PROFILE_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROFILE_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case UPDATE_PROFILE -> this.updateProfile();
                        case VIEW_INFORMATION -> this.viewInformation();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    private void updateProfile() {

    }
    private void viewInformation() {

    }
}
