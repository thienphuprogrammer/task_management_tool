package bussinesslayer.service.user.admin;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.user.IUserService;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class AdminService implements IAdminService {
    // -------------------- Properties ------------------------
    private IDao<Admin> adminDao;
    IDaoFactory adminDaoFactory;
    // -------------------- Constructor ------------------------
    public AdminService() throws Exception {
        adminDaoFactory = new DaoFactory();
        this.adminDao = adminDaoFactory.getAdminDao();
    }
    // -------------------- Getters and Setters ------------------------
    public IDao<Admin> getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(IDao<Admin> adminDao) {
        this.adminDao = adminDao;
    }
    // --------------------- Override Methods ----------------------
    @Override
    public void update(Admin admin) throws Exception {
        adminDao.update(admin);
    }

    @Override
    public void create(Admin admin) throws Exception {
        adminDao.addNew(admin);
    }

    @Override
    public void delete(int id) throws Exception {
        adminDao.delete(id);
    }

    @Override
    public Admin getById(int id) throws Exception {
        return adminDao.getById(id);
    }

    @Override
    public List<Admin> getAll() throws Exception {
        return adminDao.getAll();
    }


    @Override
    public void viewById(int id) throws Exception {
        Admin admin = getById(id);
        System.out.println("| id: " + admin.getId() + " ".repeat(40 - String.valueOf(admin.getId()).length()) + "|");
        System.out.println("| Name: " + admin.getName() + " ".repeat(40 - String.valueOf(admin.getName()).length()) + "|");
        System.out.println("| age: " + admin.getAge() + " ".repeat(36 - String.valueOf(admin.getAge()).length()) + "|");
        System.out.println("| email: " + admin.getEmail() + " ".repeat(43 - String.valueOf(admin.getEmail()).length()) + "|");
        System.out.println("| password: " + admin.getPassword() + " ".repeat(43 - String.valueOf(admin.getPassword()).length()) + "|");
        System.out.println("| phone number: " + admin.getPhoneNumber() + " ".repeat(43 - String.valueOf(admin.getPhoneNumber()).length()) + "|");
        System.out.println("| address: " + admin.getAddress() + " ".repeat(43 - String.valueOf(admin.getAddress()).length()) + "|");
    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Admin> list = getAll();
            for (Admin admin : list) {
                viewById(admin.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(int id, String password) {

    }

    @Override
    public void changeEmail(int id, String email) {

    }
}
