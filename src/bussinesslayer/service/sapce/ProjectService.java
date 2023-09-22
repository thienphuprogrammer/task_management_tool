package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Project;
import datalayer.IDao;

import java.util.List;

public class ProjectService implements ISpaceService<Project> {
    // -------------------- Properties ------------------------
    private IDao<Project> projectIDao;

    // -------------------- Constructor ------------------------
    public ProjectService() {
    }

    public ProjectService(IDao<Project> projectIDao) {
        this.projectIDao = projectIDao;
    }
    // -------------------- Getters and Setters ------------------------


    public IDao<Project> getProjectIDao() {
        return projectIDao;
    }

    public void setProjectIDao(IDao<Project> projectIDao) {
        this.projectIDao = projectIDao;
    }
    // -------------------- Methods ------------------------

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
}
