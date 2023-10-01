package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.submission.SubmissionTask;
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
        if (task == null) {
            throw new Exception("Task is not exist");
        }
        task.setMemberId(managerId);
        taskIDao.update(task);
    }
    public void reassignMember(int taskId, int newMemberId) throws Exception {
        Task task = taskIDao.getById(taskId);
        if (task == null) {
            throw new Exception("Task is not exist");
        }
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
        Task task = taskIDao.getById(id);
        if (task == null) {
            throw new Exception("Task is not exist");
        }
        return task;
    }

    @Override
    public List<Task> getAll() throws Exception {
        List<Task> list = taskIDao.getAll();
        if (list == null) {
            throw new Exception("Task is not exist");
        }
        return list;
    }
    @Override
    public void submitTask(SubmissionTask submissionTask, int taskId) {
        taskIDao.submitTask(submissionTask, taskId);
    }

    @Override
    public List<Task> getAllTasksOfMember(int sprintId, int memberId) throws Exception {
        List<Task> taskList = taskIDao.getAllTasksOfMember(sprintId, memberId);
        if (taskList == null) {
            throw new Exception("Task is not exist");
        }
        return taskList;
    }

    @Override
    public List<Task> getAllTasks(int sprintId) throws Exception {
        List<Task> taskList = taskIDao.getAllTasks(sprintId);
        if (taskList == null) {
            throw new Exception("Task is not exist");
        }
        return taskList;
    }

    @Override
    public List<SubmissionTask> getSubmissionTaskByTaskId(int taskId) throws Exception {
        List<SubmissionTask> taskList = taskIDao.getSubmissionTaskByTaskId(taskId);
        if (taskList == null) {
            throw new Exception("Task is not exist");
        }
        return taskList;
    }

}
