package bussinesslayer.service.user.admin;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.user.IUserService;

public interface IAdminService extends IUserService<Admin> {
    Admin loginAdmin(String email, String password);
}
