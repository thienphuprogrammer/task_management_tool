package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Backlog;
import datalayer.IDao;

import java.sql.SQLException;
import java.util.List;

public class BacklogService implements ISpaceService<Backlog> {
    // -------------------- Properties ------------------------
    private IDao<Backlog> backlogIDao;

    // -------------------- Constructor ------------------------
    public BacklogService() {
    }

    public BacklogService(IDao<Backlog> backlogIDao) {
        this.backlogIDao = backlogIDao;
    }

    // -------------------- Getters and Setters ------------------------

    public IDao<Backlog> getBacklogIDao() {
        return backlogIDao;
    }

    public void setBacklogIDao(IDao<Backlog> backlogIDao) {
        this.backlogIDao = backlogIDao;
    }


    // -------------------- Methods ------------------------

    @Override
    public void update(Backlog backlog) throws Exception {
        backlogIDao.update(backlog);
    }

    @Override
    public void create(Backlog backlog) throws Exception {
        backlogIDao.addNew(backlog);
    }

    @Override
    public void delete(int id) throws Exception {
        backlogIDao.delete(id);
    }

    @Override
    public Backlog getById(int id) throws Exception {
        return backlogIDao.getById(id);
    }

    @Override
    public List<Backlog> getAll() throws Exception {
        return backlogIDao.getAll();
    }
}
