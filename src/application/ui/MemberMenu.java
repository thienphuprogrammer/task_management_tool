package application.ui;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

//Member Menu
public class MemberMenu {
    public enum CHOICE_MEMBER_MENU {
        EXIT,
        VIEW_DOCUMENT,
        VIEW_REPORT,
        VIEW_PROFILE,
        VIEW_INFORMATION,
        ViEW_TASK_PROGRESS_TRACKING,

    }
    // -------------------- Properties ------------------------
    IService<Member> service;
    // -------------------- Constructor ------------------------
    public MemberMenu(IService<Member> service) {
        this.service = service;
    }
    public void processMenuForMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Member Menu");
            for (CHOICE_MEMBER_MENU choice : CHOICE_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createMember() {

    }
    private void updateMember() {

    }
    private void deleteMember() {

    }
    private void getAllMember() {

    }
    private void getMemberById() {

    }

}
