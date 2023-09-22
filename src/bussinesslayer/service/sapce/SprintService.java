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
}
