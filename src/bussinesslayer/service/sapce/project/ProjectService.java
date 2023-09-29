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
    public List<Project> getAllProjectsOfManager(int userId) throws Exception {
        return projectIDao.getAllProject(userId);
    }

    @Override
    public Project getProjectByMemberId(int projectId, int memberId) {
        return projectIDao.getProjectByMemberId(projectId, memberId);
    }

    @Override
    public List<Project> getAllProjectsOfMember(int memberId) {
        return projectIDao.getAllProjectMember(memberId);
    }

    @Override
    public Member searchMemberInProject(int memberId, int projectId) throws Exception {
        return projectIDao.searchMemberInProject(projectId, memberId);
    }
}
