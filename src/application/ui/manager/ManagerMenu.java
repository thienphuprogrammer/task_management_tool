package application.ui.manager;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.manager.IManagerService;
import bussinesslayer.service.user.manager.ManagerService;

import java.util.List;

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
    IManagerService serviceManager = new ManagerService();
    private int managerId = 1;

    public ManagerMenu(int managerId) throws Exception {
        this.managerId = managerId;
    }

    // -------------------- Constructor ------------------------
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
        try {
            List<Member> list = serviceManager.viewAllMember(managerId);
            for (Member member : list) {
                printValue("Id: " + member.getId() + " ".repeat(10 - String.valueOf(member.getId()).length()) + "|");
                printValue("Name: " + member.getName() + " ".repeat(30 - String.valueOf(member.getName()).length()) + "|");
                printValue("Email: " + member.getEmail() + " ".repeat(40 - String.valueOf(member.getEmail()).length()) + "|");
                printValue("Phone: " + member.getPhoneNumber() + " ".repeat(20 - String.valueOf(member.getPhoneNumber()).length()) + "|");
                printValue("Address: " + member.getAddress() + " ".repeat(20 - String.valueOf(member.getAddress()).length()) + "|");
                printValueln("Role: " + member.getRole() + " ".repeat(10 - String.valueOf(member.getRole()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
