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
        Backlog backlog = backlogIDao.getById(id);
        if (backlog == null) {
            throw new Exception("Backlog is not exist");
        }
        return backlog;
    }

    @Override
    public List<Backlog> getAll() throws Exception {
        List<Backlog> list = backlogIDao.getAll();
        if (list == null) {
            throw new Exception("Backlog is not exist");
        }
        return list;
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
        Task task = backlogIDao.getTaskInBacklogByTaskId(taskId);
        if (task == null) {
            throw new Exception("Task is not exist");
        }
        return task;
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
        List<Task> list = backlogIDao.getAllTasksInBacklog(backlogId);
        if (list == null) {
            throw new Exception("Task is not exist");
        }
        return list;
    }

    @Override
    public Sprint getSprintBySprintId(int sprintId) throws Exception {
        Sprint sprint = backlogIDao.getSprintBySprintId(sprintId);
        if (sprint == null) {
            throw new Exception("Sprint is not exist");
        }
        return sprint;
    }
}
