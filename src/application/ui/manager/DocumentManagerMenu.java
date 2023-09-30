package application.ui.manager;

import bussinesslayer.entity.document.Document;
import bussinesslayer.service.document.DocumentService;
import bussinesslayer.service.document.IDocumentService;

import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class DocumentManagerMenu {
    // -------------------- Properties ------------------------
    private IDocumentService docsService = new DocumentService();
    private int projectId;
    public enum CHOICE_DOCUMENT_MANAGER_MENU {
        EXIT,
        CREATE_DOCUMENT,
        UPDATE_DOCUMENT,
        DELETE_DOCUMENT,
        VIEW_ALL_DOCUMENT_IN_PROJECT
    }
    // -------------------- Constructor ------------------------

    public DocumentManagerMenu(int projectId) {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------
    public IDocumentService getDocsService() {
        return docsService;
    }

    public void setDocsService(IDocumentService docsService) {
        this.docsService = docsService;
    }
    // -------------------- Methods ------------------------

    public void processMenuForDocumentManager() throws Exception {
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
                        case UPDATE_DOCUMENT -> this.updateDocument();
                        case VIEW_ALL_DOCUMENT_IN_PROJECT -> this.viewAllDocuments();
                        case CREATE_DOCUMENT -> this.createDocument();
                        case DELETE_DOCUMENT -> this.deleteDocument();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    /*
     * update document
     * check document_id exist
     * press enter to keep old info
     * check Document exist in project
     * Ask user to confirm
     */
    private void updateDocument() {
        try {
            int documentId = readInt("Enter document id: ");
            Document doc = docsService.getDocument(projectId, documentId);
            if (doc.getProjectId() == projectId) {
                doc.setTitle(readString("Title (Enter to keep old info): ", doc.getTitle()));
                doc.setDescription(readString("Description (Enter to keep old info): ", doc.getDescription()));
                doc.setContent(readString("Content(Enter to keep old info): ", doc.getContent()));
                docsService.update(doc);
            } else {
                printValueln("Document not in project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
    /*
     * view document
     * check
     */
    private void viewAllDocuments() {
        try {
            List<Document> list = docsService.getAllDocumentsByProjectId(projectId);
            for (Document doc : list) {
                printLineSeparate("Document");
                printValue("id: " + doc.getId() + " ".repeat(40 - String.valueOf(doc.getId()).length()) + "|");
                printValue("Title: " + doc.getTitle());
                printValueln("Description: " + doc.getDescription());
                printValueln("Content: " + doc.getContent());
                printLineSeparate("");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
    * create document
    * Press enter to skip old info
    * check validation
    */
    private void createDocument() {
        try {
            Document doc = new Document();
            doc.setTitle(readString("Title: "));
            doc.setDescription(readString("Description: "));
            doc.setContent(readString("Content: "));
            doc.setProjectId(projectId);
            docsService.create(doc);
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }

    /*
    * delete document
    * check document_id exist
    * check document exist in project
    * Ask user to confirm
    */
    private void deleteDocument() {
        try {
            int documentId = readInt("Enter document id: ");
            Document document = docsService.getDocument(projectId, documentId);
            if (document != null) { // check document exist
                if(readConfirm("Do you want to delete? Y/N")) {
                    docsService.delete(documentId);
                }
            } else {
                printValueln("Document not found.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
