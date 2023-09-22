package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Task;
import datalayer.IDao;

import java.util.List;

public class TaskService implements ISpaceService<Task> {
    // -------------------- Properties ------------------------
    private IDao<Task> taskIDao;

    // -------------------- Constructor ------------------------
    public TaskService() {
    }

    public TaskService(IDao<Task> taskIDao) {
        this.taskIDao = taskIDao;
    }
    // -------------------- Getters and Setters ------------------------


    public IDao<Task> gettaskIDao() {
        return taskIDao;
    }

    public void settaskIDao(IDao<Task> taskIDao) {
        this.taskIDao = taskIDao;
    }
    // -------------------- Methods ------------------------

    @Override
    public void update(Task Task) throws Exception {
        taskIDao.update(Task);
    }

    @Override
    public void create(Task Task) throws Exception {
        taskIDao.addNew(Task);
    }

    @Override
    public void delete(int id) throws Exception {
        taskIDao.delete(id);
    }

    @Override
    public Task getById(int id) throws Exception {
        return taskIDao.getById(id);
    }

    @Override
    public List<Task> getAll() throws Exception {
        return taskIDao.getAll();
    }
}
