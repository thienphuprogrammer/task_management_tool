package bussinesslayer.service.user.admin;

import bussinesslayer.entity.user.Admin;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;
import datalayer.user.admindao.IAdminDao;

import java.util.List;

public class AdminService implements IAdminService {
    // -------------------- Properties ------------------------
    private IAdminDao adminDao;
    IDaoFactory adminDaoFactory;
    // -------------------- Constructor ------------------------
    public AdminService() throws Exception {
        adminDaoFactory = new DaoFactory();
        this.adminDao = adminDaoFactory.getAdminDao();
    }
    // -------------------- Getters and Setters ------------------------
    public IAdminDao getAdminDao() {
        return adminDao;
    }

    public void setAdminDao(IAdminDao adminDao) {
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
    public void changePassword(int id, String password) {

    }

    @Override
    public void changeEmail(int id, String email) {

    }

    @Override
    public Admin loginAdmin(String email, String password) throws Exception {
        Admin admin = adminDao.login(email, password);
        if (admin == null) {
            throw new Exception("Invalid email or password");
        }
        return admin;
    }
}
