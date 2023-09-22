package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class SubtaskManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_SUBTASK_MANAGER_MENU {
        EXIT,
        CREATE_SUBTASK,
        UPDATE_SUBTASK,
        DELETE_SUBTASK,
        VIEW_SUBTASK,
        ASSIGN_SUBTASK_TO_MEMBER,
        REASSIGN_SUBTASK_TO_MEMBER,
    }
    //  ------------------- Constructor ------------------------
    public SubtaskManagerMenu(IService<Manager> service) {
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

    public void processMenuForSubtaskManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Subtask Manager Menu");
            for (CHOICE_SUBTASK_MANAGER_MENU choice : CHOICE_SUBTASK_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_SUBTASK_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_SUBTASK_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_SUBTASK -> this.createSubtask();
                        case UPDATE_SUBTASK -> this.updateSubtask();
                        case DELETE_SUBTASK -> this.deleteSubtask();
                        case VIEW_SUBTASK -> this.viewSubtask();
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
    private void viewSubtask() {

    }
    private void createSubtask() {

    }
    private void updateSubtask() {

    }
    private void deleteSubtask() {

    }

}
