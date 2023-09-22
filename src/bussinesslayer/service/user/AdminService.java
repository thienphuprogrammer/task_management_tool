package bussinesslayer.service.user;

import bussinesslayer.entity.user.Admin;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class AdminService implements IUserService<Admin> {
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
}
