package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class MemberManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    private enum CHOICE_MEMBER_MANAGER_MENU {
        EXIT,
        ADD_MEMBER_TO_PROJECT,
        REMOVE_MEMBER_FROM_PROJECT,
        VIEW_MEMBER,
    }
    //  ------------------- Constructor ------------------------
    public MemberManagerMenu(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Getters and Setters ------------------------

    public IService<Manager> getService() {
        return service;
    }

    public void setService(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Methods ------------------------

    public void processMenuForMemberManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Member Manager Menu");
            for (CHOICE_MEMBER_MANAGER_MENU choice : CHOICE_MEMBER_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_MEMBER_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_MEMBER_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case ADD_MEMBER_TO_PROJECT -> this.addMemberToProject();
                        case REMOVE_MEMBER_FROM_PROJECT -> this.removeMemberFromProject();
                        case VIEW_MEMBER -> this.viewMember();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void addMemberToProject() {

    }
    private void removeMemberFromProject() {

    }
    private void viewMember() {

    }
}
