package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Project;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class ProjectService implements ISpaceService<Project> {
    // -------------------- Properties ------------------------
    private IDao<Project> projectIDao;
    IDaoFactory projectDapFactory;

    // -------------------- Constructor ------------------------
    public ProjectService() throws Exception {
        projectDapFactory = new DaoFactory();
        this.projectIDao = projectDapFactory.getProjectDao();
    }

    // -------------------- Getters and Setters ------------------------


    public IDao<Project> getProjectIDao() {
        return projectIDao;
    }

    public void setProjectIDao(IDao<Project> projectIDao) {
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
