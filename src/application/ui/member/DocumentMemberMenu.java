package application.ui.member;

import bussinesslayer.entity.Document;
import bussinesslayer.service.DocsService;
import bussinesslayer.service.IDocsService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class DocumentMemberMenu {
    // -------------------- Properties ------------------------
    private final IDocsService serviceDoc = new DocsService();
    private int projectId;
    public enum CHOICE_DOCUMENT_MEMBER_MENU {
        EXIT,
        VIEW_DOCUMENT
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
                        case VIEW_DOCUMENT -> this.viewDocument();
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
        try {
            Document doc = serviceDoc.getDocument(projectId);
            printLineSeparate("Document");
            printValue("id: " + doc.getId() + " ".repeat(40 - String.valueOf(doc.getId()).length()) + "|");
            printValue("Title: " + doc.getTitle());
            printValueln("Description: " + doc.getDescription());
            printValueln("Content: " + doc.getContent());
            printLineSeparate("");
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
