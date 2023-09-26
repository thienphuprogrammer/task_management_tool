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
    public void viewById(int id) throws Exception {
        Manager manager = getById(id);
        System.out.println("| id: " + manager.getId() + " ".repeat(40 - String.valueOf(manager.getId()).length()) + "|");
        System.out.println("| Name: " + manager.getName() + " ".repeat(40 - String.valueOf(manager.getName()).length()) + "|");
        System.out.println("| age: " + manager.getAge() + " ".repeat(36 - String.valueOf(manager.getAge()).length()) + "|");
        System.out.println("| email: " + manager.getEmail() + " ".repeat(43 - String.valueOf(manager.getEmail()).length()) + "|");
        System.out.println("| password: " + manager.getPassword() + " ".repeat(43 - String.valueOf(manager.getPassword()).length()) + "|");
        System.out.println("| phone number: " + manager.getPhoneNumber() + " ".repeat(43 - String.valueOf(manager.getPhoneNumber()).length()) + "|");
        System.out.println("| address: " + manager.getAddress() + " ".repeat(43 - String.valueOf(manager.getAddress()).length()) + "|");
        System.out.println("| role: " + manager.getRole() + " ".repeat(43 - String.valueOf(manager.getRole()).length()) + "|");
        System.out.println("| gender: " + manager.isGender() + " ".repeat(43 - String.valueOf(manager.isGender()).length()) + "|");

    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Manager> list = getAll();
            for (Manager manager : list) {
                viewById(manager.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public List<Member> viewAllMember(int managerId) {
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
        if(isValidPassword(manager.getPassword())) {
            throw new Exception("Invalid password");
        }
        if (isValidName(manager.getName())) {
            throw new Exception("Invalid name");
        }
        if (isValidPhoneNumber(manager.getPhoneNumber())) {
            throw new Exception("Invalid phone number");
        }
        managerDao.addNew(manager);
    }

}