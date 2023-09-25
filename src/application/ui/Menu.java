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

import static application.utilities.OutputUtil.printValueln;

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

    public static void manageAdmin(IUserService<Admin> service) throws Exception {
        AdminMenu adminMenu = new AdminMenu(service);
        adminMenu.processMenuForAdmin();
    }
    public static void manageMember(IUserService<Member> service) {
        MemberMenu memberMenu = new MemberMenu(service);
        memberMenu.processMenuForMember();
    }
    public static void manageManager(IUserService<Manager> service) {
        ManagerMenu managerMenu = new ManagerMenu(service);
        managerMenu.processMenuForManager();
    }
}
