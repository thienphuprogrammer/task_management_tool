package datalayer.user.admindao;

import bussinesslayer.entity.user.Admin;
import datalayer.IDao;

public interface IAdminDao extends IDao<Admin> {
    Admin login(String email, String password) throws Exception;
}
