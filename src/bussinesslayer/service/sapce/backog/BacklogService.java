package bussinesslayer.service.sapce.backog;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Task;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;
import datalayer.spacedao.backlogdao.IBacklogDao;

import java.util.List;

public class BacklogService implements IBacklogService {
    // -------------------- Properties ------------------------
    private IBacklogDao backlogIDao;
    IDaoFactory backlogDaoFactory;

    // -------------------- Constructor ------------------------
    public BacklogService() throws Exception {
        backlogDaoFactory = new DaoFactory();
        this.backlogIDao = backlogDaoFactory.getBacklogDao();
    }

    // -------------------- Getters and Setters ------------------------

    public IDao<Backlog> getBacklogIDao() {
        return backlogIDao;
    }

    public void setBacklogIDao(IBacklogDao backlogIDao) {
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

    @Override
    public void viewById(int id) throws Exception {
        backlogIDao.getById(id);
    }

    @Override
    public void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId) throws Exception {
        backlogIDao.addTaskInBacklogToSprint(backlogId, taskId, sprintId);
    }

    @Override
    public void createTaskInBacklog(Task task) throws Exception {
        backlogIDao.createTaskInBacklog(task);
    }

    @Override
    public Task getTaskInBacklogByTaskId(int taskId) throws Exception {
        return backlogIDao.getTaskInBacklogByTaskId(taskId);
    }

    @Override
    public void updateTaskInBacklog(Task task) throws Exception {
        backlogIDao.updateTaskInBacklog(task);
    }

    @Override
    public void deleteTaskInBacklog(int taskId) throws Exception {
        backlogIDao.deleteTaskInBacklog(taskId);
    }

    @Override
    public List<Task> getAllTasksInBacklog(int backlogId) throws Exception {
        return backlogIDao.getAllTasksInBacklog(backlogId);
    }

    @Override
    public Sprint getSprintBySprintId(int sprintId) throws Exception {
        return backlogIDao.getSprintBySprintId(sprintId);
    }
}
