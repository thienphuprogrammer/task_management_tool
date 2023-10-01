package datalayer.user.managerdao;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.IDao;

import java.util.List;

public interface IManagerDao extends IDao<Manager> {
    List<Member> getAllMembers(int managerId) throws Exception;

    Manager loginManager(String email, String password) throws Exception;

    List<Admin> getByEmail(String email) throws Exception;

    void changePassword(int id, String password) throws Exception;

    void changeEmail(int id, String email) throws Exception;
}
