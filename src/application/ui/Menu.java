package application.ui;

import application.ui.admin.AdminMenu;
import application.ui.manager.ManagerMenu;
import application.ui.member.MemberMenu;
import application.utilities.InputUtil;
import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.admin.IAdminService;
import bussinesslayer.service.user.manager.IManagerService;
import bussinesslayer.service.user.member.IMemberService;

import static application.utilities.OutputUtil.printValueln;
import static application.utilities.OutputUtil.waitForInput;

public class Menu {
    public static int getUserMenu() {
        int number = 0;
        try {
            number = InputUtil.readInt("Enter your choice: ");
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
        return number;
    }

    public static void manageAdmin() throws Exception {
        AccountAdminMenu accountAdminMenu = new AccountAdminMenu();
        Admin admin = accountAdminMenu.processMenuForAdmin();
        if (admin != null) {
            AdminMenu adminMenu = new AdminMenu(admin.getId());
            adminMenu.processMenuForAdmin();
        } else {
            printValueln("login failed");
        }

    }
    public static void manageManager() throws Exception {
        AccountManagerMenu accountManagerMenu = new AccountManagerMenu();
        Manager manager = accountManagerMenu.processMenuForManager();
        if (manager != null) {
            ManagerMenu managerMenu = new ManagerMenu(manager.getId());
            managerMenu.processMenuForManager();
        } else {
            printValueln("login failed");
        }
    }
    public static void manageMember() throws Exception {
        AccountMemberMenu accountMemberMenu = new AccountMemberMenu();
        Member member = accountMemberMenu.processMenuForMember();
        if (member != null) {
            MemberMenu memberMenu = new MemberMenu(member.getId());
            memberMenu.processMenuForMember();
        } else {
            printValueln("login failed");
        }
    }
}
