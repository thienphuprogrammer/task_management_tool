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
    private void checkDocumentNone() throws Exception {
        if (docsService.getAll().size() == 0) {
            printValueln("Document is empty.");
            docsService.create(new Doc());
        }
    }

    public void processMenuForDocumentManager() throws Exception {
        boolean exit = false;
        checkDocumentNone();
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
        docsService.viewDocumentByProjectId(projectId);
    }
}
