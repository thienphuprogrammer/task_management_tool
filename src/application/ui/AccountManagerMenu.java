package application.ui;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.user.manager.IManagerService;
import bussinesslayer.service.user.manager.ManagerService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.InputUtil.readString;
import static application.utilities.OutputUtil.*;

public class AccountManagerMenu {
    private final IManagerService managerService = new ManagerService();

    public enum CHOICE_ACCOUNT_MANAGER_MENU {
        EXIT,
        LOGIN_MANAGER,
        SIGNUP_MANAGER
    }
    public AccountManagerMenu() throws Exception {
    }
    public void processMenuForManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Manager Menu");
            for (CHOICE_ACCOUNT_MANAGER_MENU choice : CHOICE_ACCOUNT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_ACCOUNT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_ACCOUNT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case LOGIN_MANAGER -> this.loginManager();
                        case SIGNUP_MANAGER -> this.signupManager();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    public Manager loginManager() throws Exception {
        Manager manager = null;
        try {
            String email = readString("Enter your email: ");
            String password = readString("Enter your password: ");
            manager = managerService.loginManager(email, password);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
        return manager;
    }
    public void signupManager() throws Exception {
        try {
            String name = readString("Enter your name: ");
            int age = readInt("Enter your age: ");
            String email = readString("Enter your email: ");
            String password = readString("Enter your password: ");
            String PhoneNumber = readString("Enter your phone number: ");
            String address = readString("Enter your address: ");
            String gender = readString("Male or Female? Enter your gender: ");
            Manager manager = new Manager(name, age, email, password, PhoneNumber, address, "Manager",gender);
            managerService.signupManager(manager);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

}
