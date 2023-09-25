package bussinesslayer.service.user;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.service.IService;

public interface IUserService<T> extends IService<T> {
    public void viewById(int id) throws Exception;
    public void viewAll() throws Exception;

    void changePassword(int id, String password);

    void changeEmail(int id, String email);
}
