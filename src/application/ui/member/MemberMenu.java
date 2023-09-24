package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

//Member Menu
public class MemberMenu {
    public enum CHOICE_MEMBER_MENU {
        EXIT,
        // Project
        PROJECT_MEMBER
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
    private void viewDocument() {

    }
    private void viewReport() {

    }
    private void viewProject() {

    }
    private void viewInformation() {

    }
    private void viewTaskProgressTracking() {

    }
    private void viewTaskProgress() {

    }
    private void updateProfile() {

    }
    private void submitTask() {

    }
    private void marksProgress() {

    }
    private void resubmitTask() {

    }
}
