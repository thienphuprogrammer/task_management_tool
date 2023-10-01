package bussinesslayer.service.sapce.sprint;

import bussinesslayer.entity.space.Sprint;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.spacedao.sprintdao.ISprintDao;

import java.util.List;

public class SprintService implements ISprintService {
    // -------------------- Properties ------------------------
    private ISprintDao sprintIDao;
    IDaoFactory sprintDaoFactory;

    // -------------------- Constructor ------------------------

    public SprintService() throws Exception {
        sprintDaoFactory = new DaoFactory();
        this.sprintIDao = sprintDaoFactory.getSprintDao();
    }
    // -------------------- Getters and Setters ------------------------

    public ISprintDao getSprintIDao() {
        return sprintIDao;
    }

    public void setSprintIDao(ISprintDao sprintIDao) {
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
        Sprint sprint = sprintIDao.getById(id);
        if (sprint == null) {
            throw new Exception("Sprint is not exist");
        }
        return sprint;
    }

    @Override
    public List<Sprint> getAll() throws Exception {
        List<Sprint> list = sprintIDao.getAll();
        if (list == null) {
            throw new Exception("Sprint is not exist");
        }
        return list;
    }

    @Override
    public List<Sprint> getAllSprintsOfProject(int projectId) throws Exception {
        List<Sprint> sprintList = sprintIDao.getAllSprintsOfProject(projectId);
        if (sprintList == null) {
            throw new Exception("Sprint is not exist");
        }
        return sprintList;
    }

    @Override
    public List<Sprint> getAllSprintsInProjectOfMember(int projectId, int memberId) throws Exception {
        List<Sprint> sprintList = sprintIDao.getAllSprintsInProjectOfMember(projectId, memberId);
        if (sprintList == null) {
            throw new Exception("Sprint is not exist");
        }
        return sprintList;
    }
}
