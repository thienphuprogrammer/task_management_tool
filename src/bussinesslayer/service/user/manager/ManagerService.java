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
        return managerDao.getById(id);
    }

    @Override
    public List<Manager> getAll() throws Exception {
        return managerDao.getAll();
    }

    @Override
    public void changePassword(int id, String password) {
        managerDao.changePassword(id, password);
    }

    @Override
    public void changeEmail(int id, String email) throws Exception {
        List<Admin> list = managerDao.getByEmail(email);
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        managerDao.changeEmail(id, email);
    }

    @Override
    public List<Member> getAllMembers(int managerId) {
        return managerDao.viewAllMember(managerId);
    }

    @Override
    public Manager loginManager(String email, String password) {
        return managerDao.loginManager(email, password);
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
