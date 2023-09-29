package bussinesslayer.service.user;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.IService;

public interface IUserService<T> extends IService<T> {
    void changePassword(int id, String password) throws Exception;

    void changeEmail(int id, String email) throws Exception;
}
