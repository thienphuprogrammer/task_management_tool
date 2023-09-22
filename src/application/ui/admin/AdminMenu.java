package application.ui.admin;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class AdminMenu {
    public enum CHOICE_ADMIN_MENU {
        EXIT,
        CREATE_ADMIN,
        UPDATE_ADMIN,
        DELETE_ADMIN,
        GET_ALL_ADMIN,
        GET_ADMIN_BY_ID
    }

    // -------------------- Properties ------------------------
    IService<Admin> service;

    // -------------------- Constructor ------------------------
    public AdminMenu(IService<Admin> service) {
        this.service = service;
    }
    public void processMenuForAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Admin Menu");
            for (CHOICE_ADMIN_MENU choice : CHOICE_ADMIN_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();

            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_ADMIN_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_ADMIN_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_ADMIN -> this.createAdmin();
                        case UPDATE_ADMIN -> this.updateAdmin();
                        case DELETE_ADMIN -> this.deleteAdmin();
                        case GET_ALL_ADMIN -> this.getAllAdmin();
                        case GET_ADMIN_BY_ID -> this.getAdminById();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    private void createAdmin() {
    }
    private void updateAdmin() {

    }
    private void deleteAdmin() {

    }
    private void getAllAdmin() {

    }
    private void getAdminById() {

    }
}
