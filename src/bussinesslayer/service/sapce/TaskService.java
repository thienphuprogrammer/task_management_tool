package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class TaskService implements IService<Task> {
    // -------------------- Properties ------------------------
    private IDao<Task> taskIDao;
    IDaoFactory taskFactory;

    // -------------------- Constructor ------------------------
    public TaskService() throws Exception {
        taskFactory = new DaoFactory();
        this.taskIDao = taskFactory.getTaskDao();
    }

    // -------------------- Getters and Setters ------------------------
    public IDao<Task> getTaskIDao() {
        return taskIDao;
    }

    public void setTaskIDao(IDao<Task> taskIDao) {
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

    public void viewAll() throws Exception {
        try {
            List<Task> list = taskIDao.getAll();
            for (Task task : list) {
                viewById(task.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewById(int id) throws Exception {
        Task task = taskIDao.getById(id);
        System.out.println("| id: " + task.getId() + " ".repeat(40 - String.valueOf(task.getId()).length()) + "|");
        System.out.println("| Name: " + task.getName() + " ".repeat(40 - String.valueOf(task.getName()).length()) + "|");
        System.out.println("| Description: " + task.getDescription() + " ".repeat(36 - String.valueOf(task.getDescription()).length()) + "|");
        System.out.println("| Start Date: " + task.getStartDate() + " ".repeat(43 - String.valueOf(task.getStartDate()).length()) + "|");
        System.out.println("| End Date: " + task.getEndDate() + " ".repeat(43 - String.valueOf(task.getEndDate()).length()) + "|");
        System.out.println("| Status: " + task.getStatus() + " ".repeat(43 - String.valueOf(task.getStatus()).length()) + "|");
        System.out.println("| Member ID: " + task.getMemberId() + " ".repeat(43 - String.valueOf(task.getMemberId()).length()) + "|");
        System.out.println("| Sprint ID: " + task.getSprintId() + " ".repeat(43 - String.valueOf(task.getSprintId()).length()) + "|");
    }
}
