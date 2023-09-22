package bussinesslayer.service.user;

import bussinesslayer.entity.user.Manager;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class ManagerService implements IUserService<Manager> {
    private IDao<Manager> managerDao;
    IDaoFactory managerDaoFactory;

    public ManagerService() throws Exception {
        managerDaoFactory = new DaoFactory();
        this.managerDao = managerDaoFactory.getManagerDao();
    }

    @Override
    public void update(Manager manager) throws Exception {

    }

    @Override
    public void create(Manager manager) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public Manager getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Manager> getAll() throws Exception {
        return null;
    }
}
