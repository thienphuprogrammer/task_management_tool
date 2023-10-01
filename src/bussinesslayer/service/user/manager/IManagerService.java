package bussinesslayer.service.user.manager;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.IUserService;

import java.util.List;

public interface IManagerService extends IUserService<Manager> {
    List<Member> getAllMembers(int managerId) throws Exception;
    Manager loginManager(String email, String password) throws Exception;
    void signupManager(Manager manager) throws Exception;
}
