package application.ui.member;

import bussinesslayer.entity.document.Document;
import bussinesslayer.service.document.DocumentService;
import bussinesslayer.service.document.IDocumentService;

import java.util.List;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class DocumentMemberMenu {
    // -------------------- Properties ------------------------
    private final IDocumentService serviceDoc = new DocumentService();
    private int projectId;
    public enum CHOICE_DOCUMENT_MEMBER_MENU {
        EXIT,
        VIEW_ALL_DOCUMENTS
    }
    // -------------------- Constructor ------------------------

    public DocumentMemberMenu(int projectId) {
        this.projectId = projectId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForDocumentMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Document Member Menu");
            printValueMenu("Member\\Project\\Document");
            for (CHOICE_DOCUMENT_MEMBER_MENU choice : CHOICE_DOCUMENT_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_DOCUMENT_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_DOCUMENT_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_ALL_DOCUMENTS -> this.viewAllDocuments();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    /*
     * View document
     * check list is null ?
     */
    private void viewAllDocuments() {
        try {
            List<Document> list = serviceDoc.getAllDocumentsByProjectId(projectId);
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
}
