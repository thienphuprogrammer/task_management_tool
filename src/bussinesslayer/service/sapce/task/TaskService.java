package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.spacedao.taskdao.ITaskDao;

import java.util.List;

public class TaskService implements ITaskService {
    // -------------------- Properties ------------------------
    private ITaskDao taskIDao;
    IDaoFactory taskFactory;

    // -------------------- Constructor ------------------------
    public TaskService() throws Exception {
        taskFactory = new DaoFactory();
        this.taskIDao = taskFactory.getTaskDao();
    }

    // -------------------- Getters and Setters ------------------------
    public ITaskDao getTaskIDao() {
        return taskIDao;
    }

    public void setTaskIDao(ITaskDao taskIDao) {
        this.taskIDao = taskIDao;
    }
    // -------------------- Methods ------------------------
    public void assignMember(int taskId, int managerId) throws Exception {
        Task task = taskIDao.getById(taskId);
        task.setMemberId(managerId);
        taskIDao.update(task);
    }
    public void reassignMember(int taskId, int newMemberId) throws Exception {
        Task task = taskIDao.getById(taskId);
        task.setMemberId(newMemberId);
        taskIDao.update(task);
    }

    // -------------------- Override Methods ----------------------
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
    @Override
    public void submitTask(int taskId) {
        taskIDao.submitTask(taskId);
    }

    @Override
    public List<Task> getAllTaskBacklog(int backlogId) {
        return taskIDao.getAllTaskBacklog(backlogId);
    }

    @Override
    public List<Task> getAllMyTaskMember(int sprintId, int memberId) {
        return taskIDao.getAllMyTaskMember(sprintId, memberId);
    }

    @Override
    public List<Task> getTasks(int sprintId, int memberId) {
        return taskIDao.getTasksMember(sprintId, memberId);
    }

    @Override
    public List<Task> getTaskProgress(int sprintId) {
        return taskIDao.getTaskProgress(sprintId);
    }

    @Override
    public List<Task> getAllTasksMamager(int sprintId) {
        return taskIDao.getAllTasksMamager(sprintId);
    }

    @Override
    public List<Task> getAllTasks(int sprintId) {
        return taskIDao.getAllTasks(sprintId);
    }

}
