package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class AssignSubtaskManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        ASSIGN_SUBTASK_TO_MEMBER,
        REASSIGN_SUBTASK_TO_MEMBER,
    }
    // -------------------- Constructor ------------------------
    public AssignSubtaskManagerMenu(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Methods ------------------------
    public IService<Manager> getService() {
        return service;
    }

    public void setService(IService<Manager> service) {
        this.service = service;
    }
    // -------------------- Methods ------------------------
    public void processMenuForAssignSubtaskManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Assign Subtask Manager Menu");
            for (CHOICE_BACKLOG_MANAGER_MENU choice : CHOICE_BACKLOG_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_BACKLOG_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_BACKLOG_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case ASSIGN_SUBTASK_TO_MEMBER -> this.assignSubtaskToMember();
                        case REASSIGN_SUBTASK_TO_MEMBER -> this.reassignSubtaskToMember();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void assignSubtaskToMember() {

    }
    private void reassignSubtaskToMember() {

    }
}
