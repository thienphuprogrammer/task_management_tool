package application.ui.admin;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.admin.AdminService;
import bussinesslayer.service.user.admin.IAdminService;
import bussinesslayer.service.user.manager.ManagerService;
import bussinesslayer.service.user.member.IMemberService;
import bussinesslayer.service.user.member.MemberService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class AdminMenu {
    public enum CHOICE_ADMIN_MENU {
        EXIT,
        VIEW_ALL_MANAGER,
        VIEW_ALL_MEMBER,
        REPORT_PROJECT_OF_MANAGER,
        ADD_MANAGER,
    }

    // -------------------- Properties ------------------------
    private final ManagerService serviceManager = new ManagerService();
    private final IMemberService serviceMember = new MemberService();
    private IAdminService serviceAdmin = new AdminService();
    private int adminId = 0;
    // -------------------- Constructor ------------------------
    public AdminMenu(int admin) throws Exception {
        this.adminId = admin;
    }
    public void processMenuForAdmin() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Admin Menu");
            printValueMenu("Admin");
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
                        case VIEW_ALL_MANAGER -> this.viewAllManager();
                        case VIEW_ALL_MEMBER -> this.viewAllMember();
                        case REPORT_PROJECT_OF_MANAGER -> this.reportProjectManager();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    private void viewAllManager() throws Exception {
        try {
            List<Manager> list = serviceManager.getAll();
            if (list.isEmpty()) {
                printValueln("List is empty.");
            }
            else {
                for (Manager manager : list) {
                    printValue("| id: " + manager.getId() + " ".repeat(10 - String.valueOf(manager.getId()).length()) + "|");
                    printValue("| Name: " + manager.getName() + " ".repeat(20 - String.valueOf(manager.getName()).length()) + "|");
                    printValue("| age: " + manager.getAge() + " ".repeat(10 - String.valueOf(manager.getAge()).length()) + "|");
                    printValue("| email: " + manager.getEmail() + " ".repeat(30 - String.valueOf(manager.getEmail()).length()) + "|");
                    printValue("| phone number: " + manager.getPhoneNumber() + " ".repeat(20 - String.valueOf(manager.getPhoneNumber()).length()) + "|");
                    printValue("| address: " + manager.getAddress() + " ".repeat(30 - String.valueOf(manager.getAddress()).length()) + "|");
                    printValue("| role: " + manager.getRole() + " ".repeat(10 - String.valueOf(manager.getRole()).length()) + "|");
                    printValueln("| gender: " + manager.isGender() + " ".repeat(10 - String.valueOf(manager.isGender()).length()) + "|");

                }
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private  void viewAllMember() throws Exception {
        try {
            List<Member> list = serviceMember.getAll();
            if (list.isEmpty()) {
                printValueln("List is empty.");
            } else {
                for (Member member : list) {
                    printValue("| id: " + member.getId() + " ".repeat(10 - String.valueOf(member.getId()).length()) + "|");
                    printValue("| Name: " + member.getName() + " ".repeat(20 - String.valueOf(member.getName()).length()) + "|");
                    printValue("| age: " + member.getAge() + " ".repeat(10 - String.valueOf(member.getAge()).length()) + "|");
                    printValue("| email: " + member.getEmail() + " ".repeat(30 - String.valueOf(member.getEmail()).length()) + "|");
                    printValue("| phone number: " + member.getPhoneNumber() + " ".repeat(20 - String.valueOf(member.getPhoneNumber()).length()) + "|");
                    printValue("| address: " + member.getAddress() + " ".repeat(30 - String.valueOf(member.getAddress()).length()) + "|");
                    printValue("| role: " + member.getRole() + " ".repeat(10 - String.valueOf(member.getRole()).length()) + "|");
                    printValueln("| gender: " + member.isGender() + " ".repeat(10 - String.valueOf(member.isGender()).length()) + "|");
                }
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    private void reportProjectManager() throws Exception {
        ProjectAdminMenu projectAdminMenu = new ProjectAdminMenu();
        projectAdminMenu.processMenuForReportProjectAdmin();
    }
}
