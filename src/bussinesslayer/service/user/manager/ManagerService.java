package bussinesslayer.service.user.manager;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.user.managerdao.IManagerDao;

import java.util.List;

import static bussinesslayer.components.DataValidation.*;

public class ManagerService implements IManagerService {
    private IManagerDao managerDao;
    IDaoFactory managerDaoFactory;

    public ManagerService() throws Exception {
        managerDaoFactory = new DaoFactory();
        this.managerDao = managerDaoFactory.getManagerDao();
    }

    @Override
    public void update(Manager manager) throws Exception {
        managerDao.update(manager);
    }

    @Override
    public void create(Manager manager) throws Exception {
        managerDao.addNew(manager);
    }

    @Override
    public void delete(int id) throws Exception {
        managerDao.delete(id);
    }

    @Override
    public Manager getById(int id) throws Exception {
        Manager manager = managerDao.getById(id);
        if (manager == null) {
            throw new Exception("Manager is not exist");
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() throws Exception {
        List<Manager> list = managerDao.getAll();
        if (list == null) {
            throw new Exception("Manager is not exist");
        }
        return list;
    }

    @Override
    public void changePassword(int id, String password) throws Exception {
        if (!isValidPassword(password)) {
            throw new Exception("Invalid password");
        }
        managerDao.changePassword(id, password);
    }

    @Override
    public void changeEmail(int id, String email) throws Exception {
        List<Admin> list = managerDao.getByEmail(email);
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        if (!isValidEmail(email)) {
            throw new Exception("Invalid email");
        }
        managerDao.changeEmail(id, email);
    }

    @Override
    public List<Member> getAllMembers(int managerId) throws Exception {
        List<Member> list = managerDao.getAllMembers(managerId);
        if (list == null) {
            throw new Exception("Member is not exist");
        }
        return list;
    }

    @Override
    public Manager loginManager(String email, String password) throws Exception {
        Manager manager = managerDao.loginManager(email, password);
        if (manager == null) {
            throw new Exception("Manager is not exist");
        }
        return manager;
    }

    @Override
    public void signupManager(Manager manager) throws Exception {
        if(!isValidEmail(manager.getEmail())) {
            throw new Exception("Invalid email");
        }
        List<Admin> list = managerDao.getByEmail(manager.getEmail());
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        if(!isValidPassword(manager.getPassword())) {
            throw new Exception("Invalid password");
        }
        if (!isValidName(manager.getName())) {
            throw new Exception("Invalid name");
        }
        if (!isValidPhoneNumber(manager.getPhoneNumber())) {
            throw new Exception("Invalid phone number");
        }
        managerDao.addNew(manager);
    }
}
