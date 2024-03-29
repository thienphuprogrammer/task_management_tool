package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.manager.ManagerService;

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
    /*
     * View information
     */
    private void viewInformation() throws Exception {
        try {
            Manager manager = serviceManager.getById(managerId);
            printLineSeparate("Information");
            printValueMenu("Id: " + manager.getId());
            printValueMenu("Name: " + manager.getName());
            printValueMenu("Age: " + manager.getAge());
            printValueMenu("Phone number: " + manager.getPhoneNumber());
            printValueMenu("Address: " + manager.getAddress());
            printValueMenu("Email: " + manager.getEmail());
            printValueMenu("Password: " + manager.getPassword());
            printLineSeparate();
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    /*
     * Change password
     * Check password correct
     */
    private void changePassword() {
        try {
            String password = readString("Password (minimum 8 characters): ");
            serviceManager.changePassword(managerId, password);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    /*
     * Change email
     * Check email is validation
     * Check email is exist
     */
    private void changeEmail() {
        try {
            String email = readString("Email: ");
            serviceManager.changeEmail(managerId, email);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Change information
     * Press enter to keep old info
     * Check validation
     * check Gender is only Male or Female
     */
    private void changeInformation() {
        try {
            Manager manager = serviceManager.getById(managerId);
            manager.setName(readString("Name (enter to keep old name): ", manager.getName()));
            manager.setAge(readInt("Age (enter to keep old age): ", manager.getAge()));
            manager.setPhoneNumber(readString("Phone number (enter to keep old phone number): ", manager.getPhoneNumber()));
            manager.setAddress(readString("Address (enter to keep old address): ", manager.getAddress()));
            manager.setGender(readString("Gender (enter to keep old gender): ", manager.isGender()));
            serviceManager.update(manager);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
