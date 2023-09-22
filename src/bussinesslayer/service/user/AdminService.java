package bussinesslayer.service.user;

import bussinesslayer.entity.user.Admin;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class AdminService implements IUserService<Admin> {
    private IDao<Admin> adminDao;
    IDaoFactory adminDaoFactory;
    public AdminService() throws Exception {
        adminDaoFactory = new DaoFactory();
        this.adminDao = adminDaoFactory.getAdminDao();
    }

    @Override
    public void update(Admin admin) throws Exception {

    }

    @Override
    public void create(Admin admin) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public Admin getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Admin> getAll() throws Exception {
        return null;
    }
}
