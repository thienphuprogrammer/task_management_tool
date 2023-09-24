package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProfileManagerMenu {
    // -------------------- Properties ------------------------
    private IUserService<Manager> service;
    public enum CHOICE_PROFILE_MANAGER_MENU {
        EXIT,
        UPDATE_PROFILE,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_INFORMATION,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------


    public ProfileManagerMenu(IUserService<Manager> service) {
        this.service = service;
    }
    // -------------------- Getters and Setters ------------------------
    public IUserService<Manager> getService() {
        return service;
    }

    public void setService(IUserService<Manager> service) {
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
                        case CHANGE_PASSWORD -> this.changePassword();
                        case CHANGE_EMAIL -> this.changeEmail();
                        case CHANGE_INFORMATION -> this.changeInformation();
                        default -> {
                        }
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
    private void changePassword() {

    }
    private void changeEmail() {

    }
    private void changeInformation() {

    }
}
