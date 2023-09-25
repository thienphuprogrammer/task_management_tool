package application.ui.admin;

import application.ui.manager.ProjectManagerMenu;
import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.ManagerService;
import bussinesslayer.service.user.MemberService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class AdminMenu {
    private final ManagerService serviceManager = new ManagerService();
    private final IUserService<Member> serviceMember = new MemberService();
    public enum CHOICE_ADMIN_MENU {
        EXIT,
        VIEW_ALL_MANAGER,
        VIEW_ALL_MEMBER,
        REPORT_PROJECT_MANAGER,
        REPORT_SPRINT_MANAGER,
    }

    // -------------------- Properties ------------------------
    IService<Admin> service;

    // -------------------- Constructor ------------------------
    public AdminMenu(IService<Admin> service) throws Exception {
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
                        case VIEW_ALL_MANAGER -> this.viewAllManager();
                        case VIEW_ALL_MEMBER -> this.viewAllMember();
                        case REPORT_PROJECT_MANAGER -> this.reportProjectManager();
                        case REPORT_SPRINT_MANAGER -> this.reportSprintManager();
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
        serviceManager.viewAll();
    }
    private  void viewAllMember() throws Exception {
        serviceMember.viewAll();
    }
    private void reportProjectManager() throws Exception {
        ProjectAdminMenu projectAdminMenu = new ProjectAdminMenu();
        projectAdminMenu.processMenuForReportProjectAdmin();
    }
    private void reportSprintManager() throws Exception {
        SprintAdminMenu sprintAdminMenu = new SprintAdminMenu();
        sprintAdminMenu.processMenuBacklogAdmin();
    }
}
