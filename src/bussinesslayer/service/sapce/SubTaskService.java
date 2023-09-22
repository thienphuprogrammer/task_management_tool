package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.SubTask;
import datalayer.IDao;

import java.util.List;

public class SubTaskService implements ISpaceService<SubTask> {
    // -------------------- Properties ------------------------
    private IDao<SubTask> SubTaskIDao;

    // -------------------- Constructor ------------------------
    public SubTaskService() {
    }

    public SubTaskService(IDao<SubTask> SubTaskIDao) {
        this.SubTaskIDao = SubTaskIDao;
    }
    // -------------------- Getters and Setters ------------------------


    public IDao<SubTask> getSubTaskIDao() {
        return SubTaskIDao;
    }

    public void setSubTaskIDao(IDao<SubTask> SubTaskIDao) {
        this.SubTaskIDao = SubTaskIDao;
    }
    // -------------------- Methods ------------------------

    @Override
    public void update(SubTask SubTask) throws Exception {
        SubTaskIDao.update(SubTask);
    }

    @Override
    public void create(SubTask SubTask) throws Exception {
        SubTaskIDao.addNew(SubTask);
    }

    @Override
    public void delete(int id) throws Exception {
        SubTaskIDao.delete(id);
    }

    @Override
    public SubTask getById(int id) throws Exception {
        return SubTaskIDao.getById(id);
    }

    @Override
    public List<SubTask> getAll() throws Exception {
        return SubTaskIDao.getAll();
    }
}
