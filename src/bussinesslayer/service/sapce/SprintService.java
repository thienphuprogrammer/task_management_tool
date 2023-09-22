package bussinesslayer.service.sapce;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Sprint;
import datalayer.IDao;

import java.util.List;

public class SprintService implements ISpaceService<Sprint> {
    // -------------------- Properties ------------------------
    private IDao<Sprint> sprintIDao;

    // -------------------- Constructor ------------------------
    public SprintService(IDao<Sprint> sprintIDao) {
        this.sprintIDao = sprintIDao;
    }

    public SprintService() {
    }
    // -------------------- Getters and Setters ------------------------

    public IDao<Sprint> getSprintIDao() {
        return sprintIDao;
    }

    public void setSprintIDao(IDao<Sprint> sprintIDao) {
        this.sprintIDao = sprintIDao;
    }

    // -------------------- Methods ------------------------

    @Override
    public void update(Sprint sprint) throws Exception {
        sprintIDao.update(sprint);
    }

    @Override
    public void create(Sprint sprint) throws Exception {
        sprintIDao.addNew(sprint);
    }

    @Override
    public void delete(int id) throws Exception {
        sprintIDao.delete(id);
    }

    @Override
    public Sprint getById(int id) throws Exception {
        return sprintIDao.getById(id);
    }

    @Override
    public List<Sprint> getAll() throws Exception {
        return sprintIDao.getAll();
    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Sprint> list = sprintIDao.getAll();
            for (Sprint sprint : list) {
                viewById(sprint.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewById(int id) throws Exception {
        Sprint sprint = sprintIDao.getById(id);
        System.out.println("| id: " + sprint.getId() + " ".repeat(40 - String.valueOf(sprint.getId()).length()) + "|");
        System.out.println("| Name: " + sprint.getName() + " ".repeat(40 - String.valueOf(sprint.getName()).length()) + "|");
        System.out.println("| Description: " + sprint.getDescription() + " ".repeat(36 - String.valueOf(sprint.getDescription()).length()) + "|");
        System.out.println("| Start Date: " + sprint.getStartDate() + " ".repeat(43 - String.valueOf(sprint.getStartDate()).length()) + "|");
        System.out.println("| End Date: " + sprint.getEndDate() + " ".repeat(43 - String.valueOf(sprint.getEndDate()).length()) + "|");
        System.out.println("| Manager ID: " + sprint.getProjectId() + " ".repeat(43 - String.valueOf(sprint.getProjectId()).length()) + "|");

    }
}
