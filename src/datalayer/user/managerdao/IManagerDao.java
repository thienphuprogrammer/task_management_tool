package datalayer.user.managerdao;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.IDao;

import java.util.List;

public interface IManagerDao extends IDao<Manager> {
    List<Member> getAllMembers(int managerId);

    Manager loginManager(String email, String password);

    List<Admin> getByEmail(String email);

    void changePassword(int id, String password);

    void changeEmail(int id, String email);
}
