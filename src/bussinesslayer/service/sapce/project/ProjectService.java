package bussinesslayer.service.sapce.project;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.spacedao.projectdao.IProjectDao;

import java.util.ArrayList;
import java.util.List;

public class ProjectService implements IProjectService {
    // -------------------- Properties ------------------------
    private IProjectDao projectIDao;
    IDaoFactory projectDapFactory;

    // -------------------- Constructor ------------------------
    public ProjectService() throws Exception {
        projectDapFactory = new DaoFactory();
        this.projectIDao = projectDapFactory.getProjectDao();
    }

    // -------------------- Getters and Setters ------------------------


    public IProjectDao getProjectIDao() {
        return projectIDao;
    }

    public void setProjectIDao(IProjectDao projectIDao) {
        this.projectIDao = projectIDao;
    }
    // -------------------- Methods -----------------------
    public void assignManager(int projectId, int managerId) throws Exception {
        Project project = projectIDao.getById(projectId);
        project.setManagerId(managerId);
        projectIDao.update(project);
    }

    public void reassignManager(int projectId, int newManagerId) throws Exception {
        Project project = projectIDao.getById(projectId);
        project.setManagerId(newManagerId);
    }

    // --------------------- Override Methods ----------------------
    @Override
    public void update(Project project) throws Exception {
        projectIDao.update(project);
    }

    @Override
    public void create(Project project) throws Exception {
        projectIDao.addNew(project);
    }

    @Override
    public void delete(int id) throws Exception {
        projectIDao.delete(id);
    }

    @Override
    public Project getById(int id) throws Exception {
        return projectIDao.getById(id);
    }

    @Override
    public List getAll() throws Exception {
        return projectIDao.getAll();
    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Project> list = projectIDao.getAll();
            for (Project project : list) {
                viewById(project.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMemberToProject(int projectId, int memberId, int managerId) throws Exception {
        projectIDao.addMemberToProject(projectId, memberId);
    }

    @Override
    public void removeMemberFromProject(int projectId, int memberId, int managerId) throws Exception {
        projectIDao.removeMemberFromProject(projectId, memberId);
    }

    @Override
    public List<Member> getAllMembersInProject(int projectId, int managerId) throws Exception {
        List<Member> list = new ArrayList<>();
        Project project = projectIDao.getById(projectId);
        if (project.getManagerId() == managerId) {
            list = projectIDao.getAllMemberProject(projectId, managerId);
        } else {
            throw new Exception("You are not the manager of this project");
        }
        return list;
    }
    @Override
    public List<Project> getProjectMember(int memberId) throws Exception {
        return projectIDao.getProjectMember(memberId);
    }

    @Override
    public List<Member> getAllMember(int projectId, int memberId) throws Exception {
        Project project = projectIDao.getById(projectId);
        List<Member> list = new ArrayList<>();
        if (project.getManagerId() == memberId) {
            list = projectIDao.getAllMemberProject(projectId, memberId);
        } else {
            throw new Exception("You don't manager of this project");
        }
        return list;
    }

    @Override
    public List<Project> getAllProjectsOfManager(int userId) throws Exception {
        return projectIDao.getAllProject(userId);
    }

    @Override
    public Project getProjectByMemberId(int projectId, int memberId) {
        return projectIDao.getMemberByProjectId(projectId, memberId);
    }

    @Override
    public List<Project> getAllProjectsOfMember(int memberId) {
        return projectIDao.getAllProjectMember(memberId);
    }

    @Override
    public void viewById(int id) throws Exception {
        Project project = projectIDao.getById(id);
        System.out.println("| id: " + project.getId() + " ".repeat(40 - String.valueOf(project.getId()).length()) + "|");
        System.out.println("| Name: " + project.getName() + " ".repeat(40 - String.valueOf(project.getName()).length()) + "|");
        System.out.println("| Description: " + project.getDescription() + " ".repeat(36 - String.valueOf(project.getDescription()).length()) + "|");
        System.out.println("| Start Date: " + project.getStartDate() + " ".repeat(43 - String.valueOf(project.getStartDate()).length()) + "|");
        System.out.println("| End Date: " + project.getEndDate() + " ".repeat(43 - String.valueOf(project.getEndDate()).length()) + "|");
        System.out.println("| Manager ID: " + project.getManagerId() + " ".repeat(43 - String.valueOf(project.getManagerId()).length()) + "|");
    }
}
