package application.ui;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.admin.AdminService;
import bussinesslayer.service.user.IUserService;
import bussinesslayer.service.user.admin.IAdminService;
import bussinesslayer.service.user.manager.IManagerService;
import bussinesslayer.service.user.manager.ManagerService;
import bussinesslayer.service.user.member.IMemberService;
import bussinesslayer.service.user.member.MemberService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class Program {
    public enum LOGIN_CHOICE {
        EXIT,
        ACCOUNT_ADMIN,
        ACCOUNT_MANAGER,
        ACCOUNT_MEMBER
    }
    public static void main(String[] args) throws Exception {
        while (true) {
            printLineSeparate("User Menu");
            for (LOGIN_CHOICE choice : LOGIN_CHOICE.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= LOGIN_CHOICE.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (LOGIN_CHOICE.values()[choice]) {
                        case EXIT -> System.exit(0);
                        case ACCOUNT_ADMIN -> Menu.manageAdmin();
                        case ACCOUNT_MANAGER -> Menu.manageManager();
                        case ACCOUNT_MEMBER -> Menu.manageMember();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
}
