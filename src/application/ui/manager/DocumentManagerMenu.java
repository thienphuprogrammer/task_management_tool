package application.ui.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class DocumentManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> service;
    public enum CHOICE_DOCUMENT_MANAGER_MENU {
        EXIT,
        CREATE_DOCUMENT,
        DELETE_DOCUMENT,
        UPDATE_DOCUMENT,
        VIEW_DOCUMENT
    }
    // -------------------- Constructor ------------------------

    public DocumentManagerMenu(IService<Manager> service) {
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

    public void processMenuForDocumentManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Document Manager Menu");
            for (CHOICE_DOCUMENT_MANAGER_MENU choice : CHOICE_DOCUMENT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_DOCUMENT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_DOCUMENT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case CREATE_DOCUMENT -> this.createDocument();
                        case DELETE_DOCUMENT -> this.deleteDocument();
                        case UPDATE_DOCUMENT -> this.updateDocument();
                        case VIEW_DOCUMENT -> this.viewDocument();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createDocument() {

    }
    private void deleteDocument() {

    }
    private void updateDocument() {

    }
    private void viewDocument() {

    }
}
