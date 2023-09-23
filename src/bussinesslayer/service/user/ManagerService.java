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
        managerDao.getById(id);
    }

    @Override
    public List<Manager> getAll() throws Exception {
        managerDao.getAll();
    }

    @Override
    public void viewById(int id) throws Exception {
        Manager manager = getById(id);
        System.out.println("| id: " + manager.getId() + " ".repeat(40 - String.valueOf(manager.getId()).length()) + "|");
        System.out.println("| Name: " + manager.getName() + " ".repeat(40 - String.valueOf(manager.getName()).length()) + "|");
        System.out.println("| age: " + manager.getAge() + " ".repeat(36 - String.valueOf(manager.getAge()).length()) + "|");
        System.out.println("| email: " + manager.getEmail() + " ".repeat(43 - String.valueOf(manager.getEmail()).length()) + "|");
        System.out.println("| password: " + manager.getPassword() + " ".repeat(43 - String.valueOf(manager.getPassword()).length()) + "|");
        System.out.println("| phone number: " + manager.getPhone_number() + " ".repeat(43 - String.valueOf(manager.getPhone_number()).length()) + "|");
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
}
