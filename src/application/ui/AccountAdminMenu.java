package application.ui;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.user.admin.AdminService;
import bussinesslayer.service.user.admin.IAdminService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.InputUtil.readString;
import static application.utilities.OutputUtil.*;

public class AccountAdminMenu {
    private final IAdminService adminService = new AdminService();
    public enum CHOICE_ACCOUNT_ADMIN_MENU {
        EXIT,
        LOGIN_ADMIN
    }

    public AccountAdminMenu() throws Exception {
    }

    public Admin processMenuForAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Admin Menu");
            for (CHOICE_ACCOUNT_ADMIN_MENU choice : CHOICE_ACCOUNT_ADMIN_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_ACCOUNT_ADMIN_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_ACCOUNT_ADMIN_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case LOGIN_ADMIN -> {
                            return this.loginAdmin();
                        }
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
        return null;
    }
    private Admin loginAdmin() throws Exception {
        Admin admin = null;
        try {
            String email = readString("Enter your email: ");
            String password = readString("Enter your password: ");
            admin = adminService.loginAdmin(email, password);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
        return admin;
    }
}
