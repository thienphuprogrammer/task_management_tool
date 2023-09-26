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

    public static void manageAdmin(IAdminService service) throws Exception {

        AdminMenu adminMenu = new AdminMenu(service);
        adminMenu.processMenuForAdmin();
    }
    public static void manageMember(IMemberService service) {
        MemberMenu memberMenu = new MemberMenu(service);
        memberMenu.processMenuForMember();
    }
    public static void manageManager(IManagerService service) {
        ManagerMenu managerMenu = new ManagerMenu(service);
        managerMenu.processMenuForManager();
    }
}
