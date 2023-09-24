package application.ui.manager;

import bussinesslayer.entity.Doc;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IDocsService;
import bussinesslayer.service.IService;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class DocumentManagerMenu {
    // -------------------- Properties ------------------------
    private IDocsService docsService;
    private int projectId;
    public enum CHOICE_DOCUMENT_MANAGER_MENU {
        EXIT,
        CREATE_DOCUMENT,
        DELETE_DOCUMENT,
        UPDATE_DOCUMENT,
        VIEW_DOCUMENT
    }
    // -------------------- Constructor ------------------------

    public DocumentManagerMenu(int projectId) {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------
    public IDocsService getDocsService() {
        return docsService;
    }

    public void setDocsService(IDocsService docsService) {
        this.docsService = docsService;
    }
    // -------------------- Methods ------------------------

    public void processMenuForDocumentManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Document Manager Menu");
            printValueMenu("Manager\\Project\\Document");
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
    private void createDocument() throws Exception {
        String title = readString("Title: ");
        String description = readString("Description: ");
        String content = readString("Content: ");
        String projectId = readString("Project Id: ");
        Doc doc = new Doc(title, description, content, Integer.parseInt(projectId));
        docsService.create(doc);
    }
    private void deleteDocument() throws Exception {
        int id = readInt("Document Id: ");
        docsService.delete(id);
    }
    private void updateDocument() throws Exception {
        int id = readInt("Document Id: ");
        Doc doc = docsService.getById(id);
        doc.setTitle(readString("Title: "));
        doc.setDescription(readString("Description: "));
        doc.setContent(readString("Content: "));
        docsService.update(doc);
    }
    private void viewDocument() {
    }
}
