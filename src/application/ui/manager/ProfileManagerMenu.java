package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.ManagerService;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class ProfileManagerMenu {
    // -------------------- Properties ------------------------
    private IUserService<Manager> serviceManager = new ManagerService();
    private int managerId;
    public enum CHOICE_PROFILE_MANAGER_MENU {
        EXIT,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_INFORMATION,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------


    public ProfileManagerMenu(int managerId) throws Exception {
        this.managerId = managerId;
    }

    // -------------------- Getters and Setters ------------------------
    public IUserService<Manager> getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(IUserService<Manager> serviceManager) {
        this.serviceManager = serviceManager;
    }
    // -------------------- Methods ------------------------

    public void processMenuForProfileManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Profile Manager Menu");
            printValueMenu("Manager\\Profile");
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

    private void viewInformation() throws Exception {
        serviceManager.viewById(managerId);
    }
    private void changePassword() {
        String password = readString("Password: ");
        serviceManager.changePassword(managerId, password);
    }
    private void changeEmail() {
        try {
            String email = readString("Email: ");
            serviceManager.changeEmail(managerId, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void changeInformation() throws Exception {
        Manager manager = serviceManager.getById(managerId);
        manager.setName(readString("Name: "));
        manager.setAge(readInt("Age: "));
        manager.setPhoneNumber(readString("Phone number: "));
        manager.setAddress(readString("Address: "));
        manager.setGender(readString("Gender: "));
        serviceManager.update(manager);
    }
}
