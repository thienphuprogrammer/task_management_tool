package application.ui.manager;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.service.IService;

import java.time.LocalDate;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class ProjectManagerMenu {
    // -------------------- Properties ------------------------
    private IService<Manager> serviceManager;
    private IService<Project> serviceProject;
    public enum CHOICE_PROJECT_MANAGER_MENU {
        EXIT,
        EDIT_PROJECT,
        DELETE_PROJECT,
        CREATE_PROJECT,
        VIEW_PROJECT,
        ADD_MEMBER_TO_PROJECT,
        REMOVE_MEMBER_FROM_PROJECT,
        VIEW_MEMBER,
    }
    // -------------------- Constructor ------------------------

    public ProjectManagerMenu(IService<Manager> service) {
        this.serviceManager = service;
    }
    // -------------------- Getters and Setters ------------------------


    public IService<Manager> getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(IService<Manager> serviceManager) {
        this.serviceManager = serviceManager;
    }
    // -------------------- Methods ------------------------
    public void processMenuForProjectManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Project Manager Menu");
            for (CHOICE_PROJECT_MANAGER_MENU choice : CHOICE_PROJECT_MANAGER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_PROJECT_MANAGER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROJECT_MANAGER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case EDIT_PROJECT -> this.editProject();
                        case DELETE_PROJECT -> this.deleteProject();
                        case CREATE_PROJECT -> this.createProject();
                        case VIEW_PROJECT -> this.viewProject();
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
    private void editProject() throws Exception {
        int projectId = readInt("Enter project id: ");
        Project project = serviceProject.getById(projectId);
        project.setName(readString("Enter new project name: "));
        project.setDescription(readString("Enter new project description: "));
        project.setStartDate(readLocalDate("Enter new project start date: "));
        project.setEndDate(readLocalDate("Enter new project end date: "));
        serviceProject.update(project);
    }
    private void deleteProject() throws Exception {
        int projectId = readInt("Enter project id: ");
        serviceProject.delete(projectId);
    }
    private void createProject() throws Exception {
        String name = readString("Enter project name: ");
        String description = readString("Enter project description: ");
        LocalDate startDate = readLocalDate("Enter project start date: ");
        LocalDate endDate = readLocalDate("Enter project end date: ");
        //Project project = new Project(name, description, startDate, endDate, serviceManager.getAll().get(0).getId());
    }
    private void viewProject() {

    }
    private void addMemberToProject() throws Exception {
        int projectId = readInt("Enter project id: ");
        Project project = serviceProject.getById(projectId);
        int memberId = readInt("Enter member id: ");

    }
    private void removeMemberFromProject() {

    }
    private void viewMember() {

    }
}
