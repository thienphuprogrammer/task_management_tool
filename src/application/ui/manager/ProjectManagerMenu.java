package application.ui.manager;

import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.report.reportproject.IReportProjectService;
import bussinesslayer.service.report.reportproject.ReportProjectService;
import bussinesslayer.service.sapce.project.IProjectService;
import bussinesslayer.service.sapce.project.ProjectService;

import java.time.LocalDate;
import java.util.List;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class ProjectManagerMenu {
    // -------------------- Properties ------------------------
    private final IProjectService serviceProject = new ProjectService();
    private final IReportProjectService reportProjectService = new ReportProjectService();
    private int managerId;
    public enum CHOICE_PROJECT_MANAGER_MENU {
        EXIT,
        EDIT_PROJECT,
        DELETE_PROJECT,
        CREATE_PROJECT,
        VIEW_ALL_PROJECTS,
        ADD_MEMBER_TO_PROJECT,
        REMOVE_MEMBER_FROM_PROJECT,
        VIEW_ALL_MEMBER_IN_PROJECT,
        VIEW_REPORT_OF_PROJECT,
        // Backlog
        INTO_BACKLOG_MANAGER,

        // Sprint
        INTO_SPRINT_MANAGER,
        INTO_DOCUMENT_PROJECT_MANAGER
    }
    // -------------------- Constructor ------------------------

    public ProjectManagerMenu(int managerId) throws Exception {
        this.managerId = managerId;
    }

    // -------------------- Getters and Setters ------------------------

    // -------------------- Methods ------------------------
    public void processMenuForProjectManager() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Project Manager Menu");
            printValueMenu("\\Manager\\Project");
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
                        case VIEW_ALL_PROJECTS -> this.viewAllProjects();
                        case ADD_MEMBER_TO_PROJECT -> this.addMemberToProject();
                        case REMOVE_MEMBER_FROM_PROJECT -> this.removeMemberFromProject();
                        case VIEW_ALL_MEMBER_IN_PROJECT -> this.viewAllMembersInProject();
                        case INTO_BACKLOG_MANAGER -> this.processMenuForBacklogManager();
                        case INTO_SPRINT_MANAGER -> this.processMenuForSprintManager();
                        case VIEW_REPORT_OF_PROJECT -> this.viewReports();
                        case INTO_DOCUMENT_PROJECT_MANAGER -> this.documentProject();
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }

    /*
     * Edit project
     * Press enter to keep old info
     * check project exist
     * check validation
     * check project managed by manager ?
     */
    private void editProject() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project.getManagerId() == managerId) {
                project.setName(readString("Enter new project name: "));
                project.setDescription(readString("Enter new project description: "));
                project.setStartDate(readLocalDate("Enter new project start date: "));
                project.setEndDate(readLocalDate("Enter new project end date: "));
                serviceProject.update(project);
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Delete project
     * Check project exist
     * check project managed by manager
     * Ask user to confirm
     */
    private void deleteProject()  {
        try {
            int projectId = readInt("Enter project id: ");
            if (serviceProject.getById(projectId).getManagerId() == managerId) {
                serviceProject.delete(projectId);
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Create project
     * check validation
     * check start date < end date
     */
    private void createProject() throws Exception {
        try {
            String name = readString("Enter project name: ");
            String description = readString("Enter project description: ");
            LocalDate startDate = readLocalDate("Enter project start date: ");
            LocalDate endDate = readLocalDate("Enter project end date: ");
            Project project = new Project(name, description, startDate, endDate, managerId);
            serviceProject.create(project);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * View project
     * check project managed by manager
     */
    private void viewAllProjects() {
        try {
            List<Project> list = serviceProject.getAllProjectsOfManager(managerId);
            for (Project project : list) {
                printValue("id: " + project.getId() + " ".repeat(10 - String.valueOf(project.getId()).length()) + "|");
                printValue("name: " + project.getName() + " ".repeat(20 - String.valueOf(project.getName()).length()) + "|");
                printValue("start date: " + project.getStartDate() + " ".repeat(20 - String.valueOf(project.getStartDate()).length()) + "|");
                printValue("end date: " + project.getEndDate() + " ".repeat(20 - String.valueOf(project.getEndDate()).length()) + "|");
                printValue("description: " + project.getDescription() + " ".repeat(40 - String.valueOf(project.getDescription()).length()) + "|");
                printValueln("Manager id: " + project.getManagerId() + " ".repeat(170 - String.valueOf(project.getManagerId()).length()) + "|");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Add member to project
     * check member exist
     * check member managed by manager
     * check project exist
     * check project managed by manager
     */
    private void addMemberToProject() {
        try {
            int projectId = readInt("Enter project id: ");
            int memberId = readInt("Enter member id: ");
            serviceProject.addMemberToProject(projectId, memberId, managerId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Remove member from project
     * check member exist
     * check member managed by manager
     * check project exist
     * check project managed by manager
     * ask user to confirm this
     */
    private void removeMemberFromProject() {
        try {
            int projectId = readInt("Enter project id: ");
            int memberId = readInt("Enter member id: ");
            serviceProject.removeMemberFromProject(projectId, memberId, managerId);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }

    }

    /*
     * View member
     * check project exist
     * check project managed by manager
     */
    private void viewAllMembersInProject() {
        try {
            int projectId = readInt("Enter project id: ");
            List<Member> list = serviceProject.getAllMembersInProject(projectId, managerId);
            for (Member member : list) {
                printValue("id: " + member.getId() + " ".repeat(10 - String.valueOf(member.getId()).length()) + "|");
                printValue("name: " + member.getName() + " ".repeat(20 - String.valueOf(member.getName()).length()) + "|");
                printValue("email: " + member.getEmail() + " ".repeat(30 - String.valueOf(member.getEmail()).length()) + "|");
                printValue("phone: " + member.getPhoneNumber() + " ".repeat(20 - String.valueOf(member.getPhoneNumber()).length()) + "|");
                printValueln("address: " + member.getAddress() + " ".repeat(30 - String.valueOf(member.getAddress()).length()) + "|");
            }
        } catch (Exception e) {
            printValue(e.getMessage());
        }
    }

    /*
     * Into Backlog
     * check project exist
     * check project managed by manager
     */
    private void processMenuForBacklogManager() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project.getManagerId() == managerId) {
                BacklogManagerMenu backlogManagerMenu = new BacklogManagerMenu(projectId);
                backlogManagerMenu.processMenuForBacklogManager();
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Into Sprint
     * check project exist
     * check project managed by manager
     */
    private void processMenuForSprintManager()  {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project.getManagerId() == managerId) {
                SprintManagerMenu sprintManagerMenu = new SprintManagerMenu(projectId);
                sprintManagerMenu.processMenuForSprintManager();
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * View report
     * check project exist
     * check project managed by manager
     */
    private void viewReports() {
        try {
            int projectId = readInt("Enter project id: ");
            Project project = serviceProject.getById(projectId);
            if (project.getManagerId() == managerId) {
                List<ReportProject> reportProjects = reportProjectService.getReportsByProjectId(projectId);
                for (ReportProject reportProject : reportProjects) {
                    printValue("id: " + reportProject.getId() + " ".repeat(40 - String.valueOf(reportProject.getId()).length()) + "|");
                    printValue("name: " + reportProject.getDescription() + " ".repeat(40 - String.valueOf(reportProject.getDescription()).length()) + "|");
                    printValue("description: " + reportProject.getDescription() + " ".repeat(40 - String.valueOf(reportProject.getDescription()).length()) + "|");
                    printValue("Time: " + reportProject.getTime() + " ".repeat(40 - String.valueOf(reportProject.getTime()).length()) + "|");
                    printValueln("Date: " + reportProject.getDate() + " ".repeat(40 - String.valueOf(reportProject.getDate()).length()) + "|");
                }
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Into Document
     * check project exist
     * check project managed by manager
     */
    private void documentProject() {
        try {
            int projectId = readInt("Enter project id: ");
            if (serviceProject.getById(projectId).getManagerId() == managerId) {
                DocumentManagerMenu documentManagerMenu = new DocumentManagerMenu(managerId);
                documentManagerMenu.processMenuForDocumentManager();
            } else {
                printValueln("You are not manager of this project.");
            }
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
