package bussinesslayer.service.sapce.subtask;

import bussinesslayer.entity.space.Subtask;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class SubtaskService implements ISubtaskService {
    // -------------------- Properties ------------------------
    private IDao<Subtask> subTaskIDao;
    IDaoFactory subTaskDaoFactory;

    // -------------------- Constructor ------------------------
    public SubtaskService() throws Exception {
        subTaskDaoFactory = new DaoFactory();
        this.subTaskIDao = subTaskDaoFactory.getSubTaskDao();
    }

    // -------------------- Getters and Setters ------------------------


    public IDao<Subtask> getSubTaskIDao() {
        return subTaskIDao;
    }

    public void setSubTaskIDao(IDao<Subtask> subTaskIDao) {
        this.subTaskIDao = subTaskIDao;
    }
    // -------------------- Methods -----------------------
    public void assignMember(int subTaskId, int managerId) throws Exception {
        Subtask subTask = subTaskIDao.getById(subTaskId);
        subTask.setMemberId(managerId);
        subTaskIDao.update(subTask);
    }

    public void reassignMember(int subTaskId, int newMemberId) throws Exception {
        Subtask subTask = subTaskIDao.getById(subTaskId);
        subTask.setMemberId(newMemberId);
        subTaskIDao.update(subTask);
    }

    // --------------------- Override Methods ----------------------
    @Override
    public void update(Subtask SubTask) throws Exception {
        subTaskIDao.update(SubTask);
    }

    @Override
    public void create(Subtask SubTask) throws Exception {
        subTaskIDao.addNew(SubTask);
    }

    @Override
    public void delete(int id) throws Exception {
        subTaskIDao.delete(id);
    }

    @Override
    public Subtask getById(int id) throws Exception {
        return subTaskIDao.getById(id);
    }

    @Override
    public List<Subtask> getAll() throws Exception {
        return subTaskIDao.getAll();
    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Subtask> list = subTaskIDao.getAll();
            for (Subtask subTask : list) {
                viewById(subTask.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignSubtaskToMember(int subtaskId, int memberId) {

    }

    @Override
    public void reassignSubtaskToMember(int subtaskId, int memberId) {

    }

    @Override
    public void createSubtask(Subtask subtask) {

    }

    @Override
    public void viewSubtaskProject(int subtaskId, int taskId) {

    }

    @Override
    public void viewAllSubtaskProject(int taskId) {

    }

    @Override
    public void submitSubtask(int subtaskId) {

    }

    @Override
    public void viewById(int id) throws Exception {
        Subtask subTask = subTaskIDao.getById(id);
        System.out.println("| id: " + subTask.getId() + " ".repeat(40 - String.valueOf(subTask.getId()).length()) + "|");
        System.out.println("| Name: " + subTask.getName() + " ".repeat(40 - String.valueOf(subTask.getName()).length()) + "|");
        System.out.println("| Description: " + subTask.getDescription() + " ".repeat(36 - String.valueOf(subTask.getDescription()).length()) + "|");
        System.out.println("| Start Date: " + subTask.getStartDate() + " ".repeat(43 - String.valueOf(subTask.getStartDate()).length()) + "|");
        System.out.println("| End Date: " + subTask.getEndDate() + " ".repeat(43 - String.valueOf(subTask.getEndDate()).length()) + "|");
        System.out.println("| Manager ID: " + subTask.getStatus() + " ".repeat(43 - String.valueOf(subTask.getStatus()).length()) + "|");
        System.out.println("| Member ID: " + subTask.getMemberId() + " ".repeat(43 - String.valueOf(subTask.getMemberId()).length()) + "|");
        System.out.println("| Sprint ID: " + subTask.getTaskId() + " ".repeat(43 - String.valueOf(subTask.getTaskId()).length()) + "|");
    }
}