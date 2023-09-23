package application.ui.manager;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.sapce.ISpaceService;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class BacklogManagerMenu {
    // -------------------- Properties ------------------------
    private IUserService<Manager> serviceManager;
    private ISpaceService<Backlog> serviceBacklog;
    public enum CHOICE_BACKLOG_MANAGER_MENU {
        EXIT,
        CREATE_BACKLOG,
        UPDATE_BACKLOG,
        DELETE_BACKLOG,
        VIEW_BACKLOG
    }
    // -------------------- Constructor ------------------------
    public BacklogManagerMenu(IUserService<Manager> service) {
        this.serviceManager = service;
    }
    // -------------------- Getters and Setters ------------------------
    public IUserService<Manager> getServiceManager() {
        return serviceManager;
    }

    public ISpaceService<Backlog> getServiceBacklog() {
        return serviceBacklog;
    }

    public void setServiceBacklog(ISpaceService<Backlog> serviceBacklog) {
        this.serviceBacklog = serviceBacklog;
    }

    public void setServiceManager(IUserService<Manager> serviceManager) {
        this.serviceManager = serviceManager;

    }
    // -------------------- Methods ------------------------

    public void processMenuForBacklogManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Backlog Manager Menu");
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
                        case CREATE_BACKLOG -> this.createBacklog();
                        case UPDATE_BACKLOG -> this.updateBacklog();
                        case DELETE_BACKLOG -> this.deleteBacklog();
                        case VIEW_BACKLOG -> this.viewBacklog();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void createBacklog() throws Exception {
        String title = readString("Title: ");
        String description = readString("Description: ");
        String fileUrl = readString("File URL: ");
        Backlog backlog = new Backlog(title, description, fileUrl);
        serviceBacklog.create(backlog);
    }
    private void updateBacklog() throws Exception {
        int backlogId = readInt("Backlog ID: ");
        Backlog backlog = serviceBacklog.getById(backlogId);
        backlog.setTitle(readString("Title: "));
        backlog.setDescription(readString("Description: "));
        backlog.setFileURL(readString("File URL: "));
        serviceBacklog.update(backlog);
    }
    private void deleteBacklog() throws Exception {
        serviceBacklog.delete(readInt("Backlog ID: "));
    }
    private void viewBacklog() {

    }
}
